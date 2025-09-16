using System;
using System.Collections.Generic;
using System.Threading;

/// <summary>
/// Node for doubly-linked list storing key/value and links.
/// </summary>
public class DoublyLinkedNode<K, V>
{
    public K Key { get; set; }
    public V Value { get; set; }
    public DoublyLinkedNode<K, V> Prev { get; set; }
    public DoublyLinkedNode<K, V> Next { get; set; }

    public DoublyLinkedNode(K key, V value)
    {
        Key = key;
        Value = value;
    }
}

/// <summary>
/// Minimal doubly-linked list: head = most-recent, tail = least-recent.
/// Supports AddFirst, Remove, RemoveLast, Clear, and traversal.
/// </summary>
public class DoublyLinkedList<K, V>
{
    private DoublyLinkedNode<K, V> head;
    private DoublyLinkedNode<K, V> tail;
    public int Count { get; private set; }

    public void AddFirst(DoublyLinkedNode<K, V> node)
    {
        node.Prev = null;
        node.Next = head;
        if (head != null) head.Prev = node;
        head = node;
        if (tail == null) tail = head;
        Count++;
    }

    public void Remove(DoublyLinkedNode<K, V> node)
    {
        if (node == null) return;
        if (node.Prev != null) node.Prev.Next = node.Next;
        else head = node.Next;

        if (node.Next != null) node.Next.Prev = node.Prev;
        else tail = node.Prev;

        node.Prev = null;
        node.Next = null;
        Count--;
    }

    public DoublyLinkedNode<K, V> RemoveLast()
    {
        if (tail == null) return null;
        var node = tail;
        Remove(node);
        return node;
    }

    public void Clear()
    {
        head = null;
        tail = null;
        Count = 0;
    }

    public DoublyLinkedNode<K, V> First => head;
}

/// <summary>
/// Thread-safe LRU Cache using Dictionary + DoublyLinkedList.
/// ReaderWriterLockSlim allows concurrent reads; writes (insert/update/evict) use exclusive locks.
/// </summary>
public class LRUCache<K, V>
{
    private readonly int capacity;
    private readonly Dictionary<K, DoublyLinkedNode<K, V>> map;
    private readonly DoublyLinkedList<K, V> list;
    private readonly ReaderWriterLockSlim rwLock = new ReaderWriterLockSlim();

    public LRUCache(int capacity)
    {
        if (capacity <= 0) throw new ArgumentException("capacity must be > 0", nameof(capacity));
        this.capacity = capacity;
        map = new Dictionary<K, DoublyLinkedNode<K, V>>(capacity);
        list = new DoublyLinkedList<K, V>();
    }

    // Try to get value; if present mark as MRU. Returns true on hit.
    public bool TryGet(K key, out V value)
    {
        value = default(V);
        rwLock.EnterUpgradeableReadLock();
        try
        {
            if (!map.TryGetValue(key, out var node)) return false;
            value = node.Value;

            if (list.First != node)
            {
                rwLock.EnterWriteLock();
                try
                {
                    list.Remove(node);
                    list.AddFirst(node);
                }
                finally
                {
                    rwLock.ExitWriteLock();
                }
            }
            return true;
        }
        finally
        {
            rwLock.ExitUpgradeableReadLock();
        }
    }

    // Put or update value. Evict LRU if capacity exceeded.
    public void Put(K key, V value)
    {
        rwLock.EnterWriteLock();
        try
        {
            if (map.TryGetValue(key, out var existing))
            {
                existing.Value = value;
                list.Remove(existing);
                list.AddFirst(existing);
                return;
            }

            var node = new DoublyLinkedNode<K, V>(key, value);
            list.AddFirst(node);
            map[key] = node;

            if (map.Count > capacity)
            {
                var lru = list.RemoveLast();
                if (lru != null) map.Remove(lru.Key);
            }
        }
        finally
        {
            rwLock.ExitWriteLock();
        }
    }

    // Remove a key if present.
    public bool Remove(K key)
    {
        rwLock.EnterWriteLock();
        try
        {
            if (!map.TryGetValue(key, out var node)) return false;
            list.Remove(node);
            map.Remove(key);
            return true;
        }
        finally
        {
            rwLock.ExitWriteLock();
        }
    }

    // Clear all entries.
    public void Clear()
    {
        rwLock.EnterWriteLock();
        try
        {
            map.Clear();
            list.Clear();
        }
        finally
        {
            rwLock.ExitWriteLock();
        }
    }

    // Current count (thread-safe).
    public int Count
    {
        get
        {
            rwLock.EnterReadLock();
            try { return map.Count; }
            finally { rwLock.ExitReadLock(); }
        }
    }

    // Snapshot keys from most-recent -> least-recent (for debugging).
    public IList<K> SnapshotKeysMostRecentFirst()
    {
        rwLock.EnterReadLock();
        try
        {
            var res = new List<K>(map.Count);
            var cur = list.First;
            while (cur != null)
            {
                res.Add(cur.Key);
                cur = cur.Next;
            }
            return res;
        }
        finally
        {
            rwLock.ExitReadLock();
        }
    }
}
