package back.java.core.utils;

public class ApiResponseHandler {

    public static void handleResponse(String response) {
        System.out.println("Réponse de l'API : " + response);
    }

    public static void handleError(Exception e) {
        e.printStackTrace();
    }
}
