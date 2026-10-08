package pd6;

import pd6.participant.Participant;
import pd6.participant.Player;
import pd6.participant.Team;
import pd6.match.MatchResult;
import pd6.match.TournamentSystem;

public class Main {
    public static void main(String[] args) {
        var tournament = TournamentSystem.initialize();

        Player p1 = new Player("Jacek");
        Player p2 = new Player("Magda");
        Player p3 = new Player("Marek");
        Player p4 = new Player("Kasia");
        Team t1 = new Team("Ogry");
        Team t2 = new Team("Niebiescy");
        Team t3 = new Team("Smoki");

        tournament.addParticipant(p1);
        tournament.addParticipant(p2);
        tournament.addParticipant(p3);
        tournament.addParticipant(p4);
        tournament.addParticipant(t1);
        tournament.addParticipant(t2);
        tournament.addParticipant(t3);

        for (int i = 0; i < 3; i++) {
            MatchResult<Participant> matchResult = tournament.match(p1, t1);
            System.out.println(matchResult);
        }

        System.out.println(tournament.getParticipantsSortedByPoints());
        System.out.println();

        System.out.println(tournament.getAllParticipantsOfType(Player.class));
        System.out.println(tournament.getAllParticipantsOfType(Team.class));

    }
}

