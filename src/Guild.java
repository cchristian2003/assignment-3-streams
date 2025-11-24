import java.util.List;


/**
 * Represents a guild, which contains a list of adventurers.
 */
public class Guild implements Comparable<Guild>
{
    private String name;
    private List<Adventurer> adventurers;

    /**
     * Constructs a Guild with a given name and list of adventurers.
     *
     * @param name        The name of the guild.
     * @param adventurers The list of adventurers in the guild.
     */
    public Guild(String name, List<Adventurer> adventurers)
    {
        this.name = name;
        this.adventurers = adventurers;
    }

    @Override
    public int compareTo(Guild o)
    {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString()
    {
        return name + " is a guild with the following members: " + adventurers;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Adventurer> getAdventurers()
    {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers)
    {
        this.adventurers = adventurers;
    }
}
