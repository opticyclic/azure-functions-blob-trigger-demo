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

## What Is Azure Blob Storage?

Azure Blob Storage is _essentially_ a cloud filesystem, but not in the usual sense that you would be used to.

There is a lot more to it than that, [which you can read in the docs](https://docs.microsoft.com/en-us/azure/storage/blobs/storage-blobs-introduction), but this oversimplification should be enough to conceptualise the demo.

The blob storage is structured in the following way:

> Storage Account -> Container -> Blob

**Accounts** give you a unique namespace for your data.

**Containers** are like directories in a filesystem, however, you can't have a container within a container.

**Blobs** are files.

A storage account can include an unlimited number of containers, and a container can store an unlimited number of blobs.

For this simple demo we don't need to worry about the types of blobs as "Block blobs" are suitable for the text and binary data that we will deal with.
