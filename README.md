# teamcity-kotlin-example

A Hello World TeamCity Kotlin pipeline with sane defaults including:

- trigger on push to any branch
- disable build setting changes through the UI
- post status checks to GitHub (requires the parameter `%github.access.token%` be set to a personal access token)
- enable the status widget
- run on Linux agents only
