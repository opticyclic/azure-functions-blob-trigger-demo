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

## How This Demo Works

The BlobTrigger contains a path to the blobs inside a storage account.

    @BlobTrigger(.....    
    path = "documents/{name}",
    .....)

In this case the container is called `documents` and the `{name}` is the parameter that passes the blob name to the function.  

When a new blob is created inside a container, the BlobTrigger is fired.
If the Function is not running when the blob is added, the event will be queued until the Function starts again so that you don't miss any events.

## Developing Locally

Several steps are required to test and develop locally, and it won't work immediately on checkout without them.

### Tools

Install [Azurite](https://docs.microsoft.com/en-us/azure/storage/common/storage-use-azurite) to emulate the Azure Storage and enable local development.

Installing it using a VSCode extension sounds a bit weird, but it is simple to do and gives you an easier way to start and stop it.

Install [Azure Storage Explorer](https://docs.microsoft.com/en-ca/azure/vs-azure-tools-storage-manage-with-storage-explorer?tabs=linux#attach-to-a-local-emulator) to browse the storage.

It is probably best to use the snap package to install this on Ubuntu, so you don't have to install .NET.

**NOTE:** Azure Storage Explorer only works with gnome-keyring and not any other password managers such as KWallet

    sudo apt install gnome-keyring
    sudo snap connect storage-explorer:password-manager-service :password-manager-service

### Initialisation

**Azurite:** From VSCode you can either click the `[Azure Blob Service]` AND the `[Azure Queue Service]` at the bottom of the window to start the service, or you can press F1 to open the command palette and type `Azurite: Start`

**Azure Storage Explorer:** On first start it will open the Connect Dialog and at the bottom you can select `Local storage emulator`. 
You can leave the defaults or change the Display Name if you wish.

### Add A Container

In Azure Storage Explorer expand Storage Accounts and find the account in the list that matches the display name you just created. 

**_NOTE:_**  The `(Attached Containers)` account is not what you want!

Under the account you will see `Blob Containers`, right-click on this and `Create Blob Container`. 
Name it `documents` to match the code example.

### Connection Strings

The Java code refers to a connection:

    @FunctionName("blobTrigger")
    public void blobTrigger(
            @BlobTrigger(
                    name = "file",
                    dataType = "binary",
                    path = "documents/{name}",
                    connection = "AzureWebJobsStorage"

In order to connect this to Azurite, modify the `local.settings.json` to have `"AzureWebJobsStorage": "UseDevelopmentStorage=true",` e.g.

    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "UseDevelopmentStorage=true",
        "FUNCTIONS_WORKER_RUNTIME": "java"
      }
    }

This is a built-in shortcut for the full connection string to the emulator, which specifies the account name, the account key, and the emulator endpoints for each of the Azure Storage services.

## Test Files

The Azure Storage Explorer opens a tab when you click on a container (named "documents" in our case).

This allows you to upload files manually, which should then hit the breakpoints in the code.

You can stop the code, upload files and restart the code, and the files will still be triggered and hit the breakpoints.
