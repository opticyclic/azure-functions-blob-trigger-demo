# Azure Functions Blob Trigger Demo

This sample demonstrates processing data from Azure Blob Storage using Java.

## General Functions Info

A function is made up of the code and the config. (`function.json`).

For scripting languages (like JavaScript) the file has to be created manually, but for compiled languages (like Java) it is generated automatically from the annotations.

If you run `./gradlew azureFunctionsPackage` you will find the file at

    build/azure-functions/{project-name}/{function-name}/function.json

The `host.json` contains global configurations. However, this sample uses the absolute minimum to get it working.

See the [Functions Developer guide](https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference) for further info and any definitions that might be missing.

See the [Java Function Reference](https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference-java) for Java specifics.
