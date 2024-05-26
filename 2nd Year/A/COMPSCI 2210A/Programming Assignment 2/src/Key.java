public class Key
{
    private String name;
    private String kind;
    public Key(String word, String type)
    {
        name = word.toLowerCase();
        kind = type.toLowerCase();
    }
    public String getName()
    {
        return name;
    }
    public String getKind()
    {
        return kind;
    }
    public int compareTo(Key k)
    {
        if(name.compareTo(k.name) == 0 && kind.compareTo(k.kind) == 0)
            return 0;
        if(name.compareTo(k.name) < 0)
            return -1;
        if(name.compareTo(k.name) == 0 && kind.compareTo(k.kind) < 0)
            return -1;
        return 1;
    }
}
