package lab1.usecases;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class AddressGenerator implements Serializable {


    private final Random rand = new Random();
    private CompletableFuture<String> generationTask = null;

    public void generateNewAddress() {
        generationTask = CompletableFuture.supplyAsync(() -> generateAddress());
    }

    private String generateAddress() {
        try {
            Thread.sleep(5000);
            return "LT-" + rand.nextInt(99999);
        } catch (InterruptedException ignored) {
            return null;
        }

    }

    public boolean isGenerationRunning() {
        return generationTask != null && !generationTask.isDone();
    }

    public String getAddressGenerationStatus() throws ExecutionException, InterruptedException {
        if (generationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Generating address...";
        }
        return "Address: " + generationTask.get();
    }

    public String getResult() throws ExecutionException, InterruptedException {
        return generationTask.get();
    }
}
