package core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import back.java.core.services.AuthService;
import back.java.core.services.AuthService.LoginResponse;
import back.java.core.utils.HttpClientUtil;
import back.java.core.utils.TokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private AuthService authService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        authService = new AuthService();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String username = "testuser";
        String password = "testpassword";
        String token = "mockToken";

        LoginResponse mockResponse = new LoginResponse();
        mockResponse.setToken(token);

        String mockResponseJson = objectMapper.writeValueAsString(mockResponse);
        try (MockedStatic<HttpClientUtil> mockedHttpClientUtil = mockStatic(HttpClientUtil.class);
             MockedStatic<TokenManager> mockedTokenManager = mockStatic(TokenManager.class)) {

            mockedHttpClientUtil.when(() -> HttpClientUtil.sendPostRequest(any(String.class), any(String.class), any()))
                    .thenReturn(mockResponseJson);

            TokenManager mockTokenManager = mock(TokenManager.class);
            mockedTokenManager.when(TokenManager::getInstance).thenReturn(mockTokenManager);

            String resultToken = authService.login(username, password);

            assertEquals(token, resultToken);
            verify(mockTokenManager).setToken(token);
        }
    }

    @Test
    public void testLoginFailure() throws Exception {
        String username = "testuser";
        String password = "wrongpassword";

        try (MockedStatic<HttpClientUtil> mockedHttpClientUtil = mockStatic(HttpClientUtil.class)) {
            mockedHttpClientUtil.when(() -> HttpClientUtil.sendPostRequest(any(String.class), any(String.class), any()))
                    .thenThrow(new RuntimeException("Invalid credentials"));

            String resultToken = authService.login(username, password);

            assertNull(resultToken);
        } catch (RuntimeException e) {
            assertEquals("Invalid credentials", e.getMessage());
        }
    }

    @Test
    public void testSignupSuccess() throws Exception {
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "testpassword";
        String token = "mockToken";

        LoginResponse mockResponse = new LoginResponse();
        mockResponse.setToken(token);

        String mockResponseJson = objectMapper.writeValueAsString(mockResponse);

        try (MockedStatic<HttpClientUtil> mockedHttpClientUtil = mockStatic(HttpClientUtil.class);
             MockedStatic<TokenManager> mockedTokenManager = mockStatic(TokenManager.class)) {

            mockedHttpClientUtil.when(() -> HttpClientUtil.sendPostRequest(eq("http://localhost:3000/auth/signup"), any(String.class), any()))
                    .thenReturn(""); // Return empty response for signup

            mockedHttpClientUtil.when(() -> HttpClientUtil.sendPostRequest(eq("http://localhost:3000/auth/login"), any(String.class), any()))
                    .thenReturn(mockResponseJson);

            TokenManager mockTokenManager = mock(TokenManager.class);
            mockedTokenManager.when(TokenManager::getInstance).thenReturn(mockTokenManager);

            String resultToken = authService.signup(username, email, password);

            assertEquals(token, resultToken);
            verify(mockTokenManager).setToken(token);
        }
    }

    @Test
    public void testLogout() {
        String token = "mockToken";

        try (MockedStatic<HttpClientUtil> mockedHttpClientUtil = mockStatic(HttpClientUtil.class);
             MockedStatic<TokenManager> mockedTokenManager = mockStatic(TokenManager.class)) {

            TokenManager mockTokenManager = mock(TokenManager.class);
            when(mockTokenManager.getToken()).thenReturn(token);
            mockedTokenManager.when(TokenManager::getInstance).thenReturn(mockTokenManager);

            authService.logout();

            verify(mockTokenManager).clearToken();
            mockedHttpClientUtil.verify(() -> HttpClientUtil.sendDeleteRequest(any(String.class), eq(token)));
        }
    }
}
