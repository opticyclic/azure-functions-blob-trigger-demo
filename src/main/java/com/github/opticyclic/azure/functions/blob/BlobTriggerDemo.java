package com.github.opticyclic.azure.functions.blob;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

/**
 * Demonstrate Azure Functions with a Blob trigger.
 */
public class BlobTriggerDemo {

    /**
     * This function will be invoked when a new or updated blob is detected at the specified path.
     * The blob contents are provided as an input to this function.
     * The <code>@BindingName</code> annotation gives us the file name from the metadata of the blob.
     */
    @FunctionName("blobTrigger")
    public void blobTrigger(
            @BlobTrigger(
                    name = "file",
                    dataType = "binary",
                    path = "documents/{name}",
                    connection = "AzureWebJobsStorage"
            ) byte[] content,
            @BindingName("name") String filename,
            final ExecutionContext context
    ) {
        context.getLogger().info(
                "Java Blob trigger function processed a blob. \n" +
                        "  Name: " + filename + "\n" +
                        "    Size: " + content.length + " Bytes");
    }
}
