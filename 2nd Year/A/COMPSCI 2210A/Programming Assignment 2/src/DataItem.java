public class DataItem
{
    private Key theKey;
    private String content;
    public DataItem(Key k, String data)
    {
        theKey = new Key(k.getName(),k.getKind());
        content = data;
    }
    public Key getKey()
    {
        return theKey;
    }
    public String getContent()
    {
        return content;
    }
}
