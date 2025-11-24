import java.util.*;
import java.util.stream.Collectors;

/**
 * Application demonstrating the use of streams to manage guilds and adventurers.
 */
public class GuildStreamApp
{
    /**
     * Returns a list of sample Guild data with adventurers.
     *
     * @return List of Guild objects.
     */
    public static List<Guild> getData()
    {
        ArrayList<Guild> guilds = new ArrayList<>();

        Adventurer a1 = new Adventurer("Pocket", 19, "Wizard", 2000.0, List.of(Skill.STEALTH, Skill.NECROMANCY, Skill.RUNECRAFTING));
        Adventurer a2 = new Adventurer("Lash", 35, "Rogue", 3500.0, List.of(Skill.BLACKSMITHING, Skill.THIEVERY));
        Adventurer a3 = new Adventurer("Vindicta", 21, "Archer", 1000.0, List.of(Skill.ARCHERY, Skill.NECROMANCY, Skill.THIEVERY));
        Adventurer a4 = new Adventurer("Mina", 22, "Rogue", 20000.0, List.of(Skill.ARCHERY, Skill.THIEVERY, Skill.STEALTH));

        Adventurer a5 = new Adventurer("Bebop", 40, "Wizard", 400.0, List.of(Skill.ARCHERY, Skill.BLACKSMITHING));
        Adventurer a6 = new Adventurer("Kelvin", 34, "Healer", 3000.0, List.of(Skill.HEALING, Skill.MEMECRAFTING, Skill.SWORDSMANSHIP));
        Adventurer a7 = new Adventurer("Abrams", 28, "Warrior", 6000.0, List.of(Skill.SWORDSMANSHIP, Skill.HORSEMANSHIP));
        Adventurer a8 = new Adventurer("Dynamo", 30, "Wizard", 100.0, List.of(Skill.HEALING, Skill.MEMECRAFTING, Skill.SWORDSMANSHIP, Skill.RUNECRAFTING));

        guilds.add(new Guild("Amber Hand", new ArrayList<>(List.of(a1, a2, a3, a4))));
        guilds.add(new Guild("Sapphire Flame", new ArrayList<>(List.of(a5, a6, a7, a8))));

        return guilds;
    }

    /**
     * Returns a list of adventurers that have a specific skill.
     *
     * @param guilds List of guilds to search in.
     * @param skill  Skill to filter by.
     * @return List of Adventurer objects with the skill.
     */
    public static List<Adventurer> filterBySkill(List<Guild> guilds, Skill skill)
    {
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getSkills().contains(skill))
                .collect(Collectors.toList());
    }

    /**
     * Groups adventurers by their role and prints each group.
     *
     * @param guilds List of guilds.
     * @return Map of role to adventurers with that role.
     */
    public static Map<String, List<Adventurer>> groupByRole(List<Guild> guilds)
    {
        Map<String, List<Adventurer>> groups = guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .collect(Collectors.groupingBy(Adventurer::getRole));

        groups.forEach((role, adventurers) ->
        {
            System.out.println("Role: " + role);
            adventurers.forEach(a -> System.out.println("  - " + a.getName()));
        });

        return groups;
    }

    /**
     * Finds the adventurer with the most skills.
     *
     * @param guilds List of guilds.
     * @return Adventurer with the most skills, or null if none exist.
     */
    public static Adventurer findMostSkilled(List<Guild> guilds)
    {
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .max(Comparator.comparing(a -> a.getSkills().size()))
                .orElse(null);
    }

    /**
     * Prints the guilds ranked by the average age of their adventurers.
     *
     * @param guilds List of guilds.
     */
    public static void rankByAverageAge(List<Guild> guilds)
    {
        guilds.stream()
                .sorted(Comparator.comparingDouble(g ->
                        g.getAdventurers().stream()
                                .mapToInt(Adventurer::getAge)
                                .average()
                                .orElse(0)
                ))
                .forEach(g -> System.out.println(g.getName()));
    }

    /**
     * Counts how many adventurers know each skill.
     *
     * @param guilds List of guilds.
     * @return Map of Skill to count of adventurers who know it.
     */
    public static Map<Skill, Long> skillCountMap(List<Guild> guilds)
    {
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .flatMap(a -> a.getSkills().stream())
                .collect(Collectors.groupingBy(
                        skill -> skill,
                        Collectors.counting())
                );

    }

    /**
     * Adds a 20% gold bonus to adventurers who have earned less than 1000 gold.
     *
     * @param guilds List of guilds.
     */
    public static void bonusGoldEvent(List<Guild> guilds)
    {
        guilds.stream()
        .flatMap(g -> g.getAdventurers().stream())
        .filter(a -> a.getGoldEarned() < 1000)
        .forEach(a -> a.setGoldEarned((a.getGoldEarned() + a.getGoldEarned() * 0.20)));
    }


    public static void main( String[] args )
    {
        List<Guild> guildData = getData();

        System.out.println("Filtered Adventurers by Skill (Archery)\n");
        System.out.println(filterBySkill(guildData, Skill.ARCHERY));

        System.out.println("\nGroups Adventurers By Role\n");
        groupByRole(guildData);

        System.out.println("\nFinds the Adventurer with the Most Skills\n");
        System.out.println(findMostSkilled(guildData));

        System.out.println("\nRanks Guilds by the Average Age\n");
        rankByAverageAge(guildData);

        System.out.println("\nShows How Many Adventurers Know Each Skill\n");
        skillCountMap(guildData).forEach((skill, count) ->
                System.out.println(skill + " - " + count));

        bonusGoldEvent(guildData);
    }
}
