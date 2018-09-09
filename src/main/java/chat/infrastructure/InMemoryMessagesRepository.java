package chat.infrastructure;

import chat.domain.model.ChatMessage;
import chat.domain.model.ChatUser;
import chat.domain.port.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMessagesRepository implements MessageRepository {

    private List<ChatMessage> chatMessageList;


    public InMemoryMessagesRepository() {
        this.chatMessageList = new ArrayList<>();
    }

    @Override
    public void saveMassage(ChatMessage massage) {
        chatMessageList.add(massage);
    }

    @Override
    public List<ChatMessage> findMessagesFor(ChatUser chatUser) {

        // zwraca liste gdzie uzytkownik byl wysylajacym i odbierajacym wiadomosci

        //    @Override
//    public List<ChatMessage> findMessagesFor(ChatUser chatUser) {
//        return chatMessageList.stream()
//                .filter(u -> !(u.getSender().equals(chatUser) ||
//                        u.getReceiver().equals(chatUser)))
//                .collect(Collectors.toList());
//    }


//        List<ChatMessage> messagesList = null;
//
//        for (ChatMessage chatMessage : chatMessageList) {
//            if (chatMessage.getSender().equals(chatUser) ||
//                    chatMessage.getReceiver().equals(chatUser)) {
//                messagesList.add(chatMessage);
//            }
//        }
//        return messagesList;


        return this.chatMessageList.stream()
                .filter(e ->
                        e.getSender().equals(chatUser) ||
                        e.getReceiver().equals(chatUser))
                .collect(Collectors.toList());
    }
}


