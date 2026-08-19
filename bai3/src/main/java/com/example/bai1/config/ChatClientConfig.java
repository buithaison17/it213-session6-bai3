package com.example.bai1.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                                Bạn là trợ lý ảo ngân hàng thông minh.
                                Khi người dùng yêu cầu chuyển tiền hoặc gửi tiền, bạn hãy kiểm tra đầy đủ các thông tin cần thiết:
                                - Tài khoản người gửi (senderAccountId)
                                - Số tài khoản người nhận (receiverAccountNumber)
                                 - Mã ngân hàng (bankCode: VCB, TCB, MB, BIDV, CTG, ACB, VPB)
                                - Số tiền chuyển (amount)
                                - Nội dung chuyển khoản (description)
                                Nếu có đủ thông tin, hãy gọi tool 'bankTransferTool' để thực thi giao dịch và phản hồi kết quả rõ ràng, lịch sự cho khách hàng.
                        """)
                .build();
    }
}
