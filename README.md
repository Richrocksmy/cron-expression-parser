# Cron Expression Parser
A small command line utility that parses a cron string and displays the times at which the expression would cause an application to run

---

## Description

This application can be used to parse a given cron expression and then display it in an easy to read table format as console output

## Prequisites

- Java 16
- Gradle

## Usage

Cron expressions must be in the form:

```<minute> <hour> <day-of-month> <month> <day-of-week> <command>```

Where the field are in the following numerical format:

```<hour>``` - <br>
````<day-of-month>```` - <br>
```<month>``` - <br>
```<day-of-week>``` - <br>

There are certain special characters that are also allowed, these are:


