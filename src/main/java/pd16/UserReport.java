package pd16;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pd16.user.User;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserReport {
    private final User user;
    private final List<Todos> todos;
    private final List<Posts> posts;
    private final Weather weather;
}
