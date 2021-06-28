# teamcity-kotlin-example

A Hello World TeamCity Kotlin pipeline with sane defaults including:

- triggers on push to any branch
- disables build setting changes through the UI
- posts status checks to GitHub (requires a personal access token set in `%github.access.token%`)
- enables the status widget
- runs on Linux agents
