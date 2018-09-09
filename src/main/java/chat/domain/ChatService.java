package chat.domain;

import chat.api.InvalidChatUserException;
import chat.api.InvalidCommandException;
import chat.domain.model.ChatCommand;
import chat.domain.model.ChatMessage;
import chat.domain.model.ChatUser;
import chat.domain.port.MessageRepository;
import chat.domain.port.UsersLog;
import chat.domain.port.UsersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatService {

    public static final String SPACE_SEPARATOR = " ";
    private UsersRepository usersRepository;
    private UsersLog usersLog;
    private MessageRepository messageRepository;

    public ChatService(UsersRepository usersRepository, MessageRepository messageRepository) {


        this.usersRepository = usersRepository;
        this.messageRepository = messageRepository;
    }

    public String handleMessage(String message, ChatUser chatUser) {
        String[] splitMessage = message.split(SPACE_SEPARATOR);
        String command = splitMessage[0];

        ChatCommand chatCommand = ChatCommand.getByCommandName(command);

        switch (chatCommand) {
            case LIST_USERS:
                return handleListUsers();


            case SEND_MASSAGE:
                return handleSentMessage(chatUser, splitMessage);

            case MASSAGES:

                if (splitMessage.length == 1) {
                    List<ChatMessage> messages = messageRepository.findMessagesFor(chatUser);
                    Map<ChatUser, Integer> map = new HashMap<>();
                    for (ChatMessage chatMessage : messages) {
                       ChatUser interlocutor = getInterlocutorFor(chatUser,chatMessage);
                        if (!map.containsKey(interlocutor)) {
                            map.put(interlocutor,0);
                        }
                        Integer currentCounter = map.get(interlocutor);
                        currentCounter++;
                        map.put(interlocutor,currentCounter);
                    }
                    return aggregateToString(map);
                }

            case NEW_MASSAGES:


            default:
                return "";
        }
    }

    private String aggregateToString(Map<ChatUser,Integer> aggregates){


        // agregaty "cos grupuje cos" w naszym przypadku grupujemy a Imie i Libcza
//        for (Map.Entry<ChatUser,Integer> entry: aggregates).entrySet()){
//            ChatUser k = entry.getKey();
//            Integer v = entry.getValue();
//        }
        StringBuilder stringBuilder = new StringBuilder();

        //for each
        aggregates.forEach((k,v) -> {
           stringBuilder.append(";")
                   .append(k.getName())
                   .append(" (")
                   .append(v)
                   .append(")");
        });
        return stringBuilder.toString();
    }

    private ChatUser getInterlocutorFor(ChatUser chatUser, ChatMessage chatMessage){
        ChatUser sender = chatMessage.getSender();
        ChatUser receiver = chatMessage.getReceiver();

        if(sender.equals(chatUser)){
            return receiver;
        }else {
            return sender;
        }
    }

    private String handleListUsers() {
        List<ChatUser> users = usersRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (ChatUser user : users) {
            stringBuilder
                    .append(";")
                    .append(user.getName());
        }
        return stringBuilder.substring(1);
    }

    private String handleSentMessage(ChatUser sender, String[] splitMessage) {

        String receiveName = splitMessage[1];
        ChatUser receiver = usersRepository.findByName(receiveName);
        String sendMassage = getMassageFromMassage(splitMessage);

        ChatMessage chatMessage = new ChatMessage(receiver, sender, sendMassage);
        messageRepository.saveMassage(chatMessage);
        System.out.println("[ " + sender.getName() + " ] - > [ "
                + receiver.getName() + " ] \" " + sendMassage + "\"");
        return "Message sent";
    }

    private String getMassageFromMassage(String[] splitMessage) {
        StringBuilder stringBuilder = null;

        for (int i = 2; i < splitMessage.length; i++) {

            stringBuilder.append(SPACE_SEPARATOR);
            stringBuilder.append(splitMessage[i]);
        }
        return stringBuilder.substring(1);
    }

    public ChatUser authenticate(String massage, String address) {
        String[] splitMassage = massage.split(SPACE_SEPARATOR);
        System.out.println(splitMassage[0]);
        if (!"hello".equalsIgnoreCase(splitMassage[0])) {
            throw new InvalidCommandException("Invalid command: " + splitMassage[0]);
        }
        String name = splitMassage[1];


        ChatUser chatUser = usersRepository.findByAddress(address);
        if (chatUser == null) {
            return usersRepository.addUser(new ChatUser(name, address));
        } else {
            if (chatUser.getName().equalsIgnoreCase(name)) {
                return chatUser;
            } else {
                throw new InvalidChatUserException("Invalid name " + name);
            }
        }
    }
}
