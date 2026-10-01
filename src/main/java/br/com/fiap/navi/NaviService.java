package br.com.fiap.navi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class NaviService {

    private final ChatClient chatClient;

    public NaviService(ChatClient.Builder buider) {
        this.chatClient = buider.build();
    }

    public String translate(String text, String style) {
        String systemMessage = """
            Você é o Navi, um tradutor de textos universal.
            Sua tarefa é reescrever o texto do usuário no estilo: %s.
            Mantenha o sentido original, responda apenas com o texto
            traduzido, sem explicações, e use o mesmo idioma do texto original.
            """.formatted(style);

        return chatClient.prompt()
                .system(systemMessage)
                .user(text)
                .call()
                .content();
    }
}
