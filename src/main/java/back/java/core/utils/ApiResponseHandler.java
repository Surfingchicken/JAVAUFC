package back.java.core.utils;

public class ApiResponseHandler {

    public static void handleResponse(String response) {
        // Logique de gestion de la réponse
        System.out.println("Réponse de l'API : " + response);
    }

    public static void handleError(Exception e) {
        // Logique de gestion des erreurs
        e.printStackTrace();
    }
}
