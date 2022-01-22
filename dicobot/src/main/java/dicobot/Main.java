package dicobot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static JDA jda;
    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createDefault("").build();  //봇 토큰
        jda.getPresence().setStatus(OnlineStatus.OFFLINE);   //상태표시-(현)온라인
        jda.getPresence().setActivity(Activity.playing("개발하는 단계~"));    //~하는중 표시글

        jda.addEventListener(new Main());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");   //사용자가 입력시 공백마다 배열에 저장
        String prefix = "!";    //!명령어입력

        if(!event.getAuthor().isBot()) {
            if (args[0].equals(prefix + "p")) {
                if (args.length == 1) event.getChannel().sendMessage("!p <제목>").queue();   //조건 미 입력시 자기 명령어도 인식(=도배)
                else {
                    event.getChannel().sendMessage(args[1]+"을 재생하겠습니다~").queue();
                }
            }
        }

    }
}
