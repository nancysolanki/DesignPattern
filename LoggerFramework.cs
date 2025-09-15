using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;

/// <summary>
/// Represents severity levels for logging.
/// </summary>
public enum LogLevel
{
    DEBUG,
    INFO,
    WARN,
    ERROR
}

/// <summary>
/// Encapsulates a single log event with metadata: timestamp, logger name, level, message.
/// </summary>
public class LogEvent
{
    public DateTime Timestamp { get; set; }
    public string LoggerName { get; set; }
    public LogLevel Level { get; set; }
    public string Message { get; set; }

    public LogEvent(string loggerName, LogLevel level, string message)
    {
        Timestamp = DateTime.UtcNow;
        LoggerName = loggerName;
        Level = level;
        Message = message;
    }
}

/// <summary>
/// Layout/Formatter interface. Different implementations define how log events are formatted into strings.
/// </summary>
public interface ILogLayout
{
    string Format(LogEvent logEvent);
}

/// <summary>
/// A simple layout: "[timestamp] [level] logger - message".
/// </summary>
public class SimpleLayout : ILogLayout
{
    public string Format(LogEvent e)
    {
        return $"[{e.Timestamp:yyyy-MM-dd HH:mm:ss}] [{e.Level}] {e.LoggerName} - {e.Message}";
    }
}

/// <summary>
/// JSON layout using System.Text.Json for structured logs.
/// </summary>
public class JsonLayout : ILogLayout
{
    public string Format(LogEvent e)
    {
        return JsonSerializer.Serialize(e);
    }
}

/// <summary>
/// Appender abstraction. Each appender takes a formatted string and writes it to a destination.
/// </summary>
public interface IAppender
{
    ILogLayout Layout { get; set; }
    void Append(LogEvent logEvent);
}

/// <summary>
/// ConsoleAppender writes logs to standard output.
/// </summary>
public class ConsoleAppender : IAppender
{
    public ILogLayout Layout { get; set; }

    public ConsoleAppender(ILogLayout layout)
    {
        Layout = layout;
    }

    public void Append(LogEvent e)
    {
        Console.WriteLine(Layout.Format(e));
    }
}

/// <summary>
/// FileAppender writes logs to a specified file.
/// </summary>
public class FileAppender : IAppender
{
    public ILogLayout Layout { get; set; }
    private string filePath;

    public FileAppender(ILogLayout layout, string filePath)
    {
        Layout = layout;
        this.filePath = filePath;
    }

    public void Append(LogEvent e)
    {
        File.AppendAllText(filePath, Layout.Format(e) + Environment.NewLine);
    }
}

/// <summary>
/// Logger class. Provides methods to log at different levels and dispatches events to configured appenders.
/// </summary>
public class Logger
{
    private string name;
    private List<IAppender> appenders = new List<IAppender>();

    public Logger(string name)
    {
        this.name = name;
    }

    public void AddAppender(IAppender appender)
    {
        appenders.Add(appender);
    }

    private void Log(LogLevel level, string message)
    {
        var logEvent = new LogEvent(name, level, message);
        foreach (var appender in appenders)
        {
            appender.Append(logEvent);
        }
    }

    // Convenience methods
    public void Debug(string msg) => Log(LogLevel.DEBUG, msg);
    public void Info(string msg) => Log(LogLevel.INFO, msg);
    public void Warn(string msg) => Log(LogLevel.WARN, msg);
    public void Error(string msg) => Log(LogLevel.ERROR, msg);
}

/// <summary>
/// LoggerFactory is a singleton that provides loggers by name.
/// Ensures same logger instance is reused and can be preconfigured with appenders.
/// </summary>
public class LoggerFactory
{
    private static LoggerFactory _instance = new LoggerFactory();
    public static LoggerFactory Instance => _instance;

    private Dictionary<string, Logger> loggers = new Dictionary<string, Logger>();

    private LoggerFactory() { }

    public Logger GetLogger(string name)
    {
        if (!loggers.ContainsKey(name))
        {
            loggers[name] = new Logger(name);
        }
        return loggers[name];
    }
}

/// <summary>
/// Demo class to show usage of logging framework.
/// </summary>
public class Demo
{
    public static void Main()
    {
        var logger = LoggerFactory.Instance.GetLogger("MyApp");

        // Add appenders
        logger.AddAppender(new ConsoleAppender(new SimpleLayout()));
        logger.AddAppender(new FileAppender(new JsonLayout(), "logs.txt"));

        // Log some messages
        logger.Debug("This is a debug message");
        logger.Info("Application started successfully.");
        logger.Warn("Low disk space warning.");
        logger.Error("Unhandled exception occurred!");
    }
}
