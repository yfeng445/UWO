import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TextInterface
{
    public static void main(String [] args) {
        String filename = "small.txt";
        OrderedDictionary orderedDict = new OrderedDictionary();
        String strLine = null,content = null,name = null,kind = null;
        int index = 0;
        StringReader  keyboard  = new StringReader();
        try
        {
            File file = new File(filename);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while(null != (strLine = bufferedReader.readLine()))
            {
                if(index == 0)
                {
                    name = strLine.trim();
                    index += 1;
                }
                else
                {
                    content = strLine.trim();
                    index = 0;
                    if(content.endsWith(".wav") || content.endsWith(".mid"))
                        kind = "sound";
                    else if(content.endsWith(".gif") || content.endsWith(".jpg"))
                        kind = "picture";
                    else if(content.endsWith(".exe"))
                        kind = "program";
                    else
                        kind = "definition";
                    DataItem item = new DataItem(new Key(name,kind),content);
                    orderedDict.put(item);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        while(true)
        {
            strLine = keyboard .read("Enter next command: ");
            String[] command = strLine.trim().split(" ");
            if(command[0].compareTo("get") == 0 && command.length == 2)
            {
                DataItem item;
                boolean find = false;
                item = orderedDict.get(new Key(command[1],"definition"));
                if(item != null)
                {
                    System.out.println(item.getContent());
                    find = true;
                }
                item = orderedDict.get(new Key(command[1],"sound"));
                if(item != null)
                {
                    SoundPlayer sp = new SoundPlayer();
                    try {
                        sp.play(item.getContent());
                    } catch (MultimediaException e) {
                        e.printStackTrace();
                    }
                    find = true;
                }
                item = orderedDict.get(new Key(command[1],"picture"));
                if(item != null)
                {
                    find = true;
                    PictureViewer pv = new PictureViewer();
                    try {
                        pv.show(item.getContent());
                    } catch (MultimediaException e) {
                        e.printStackTrace();
                    }
                }
                item = orderedDict.get(new Key(command[1],"program"));
                if(item != null)
                {
                    find = true;
                    RunCommand rc = new RunCommand();
                    rc.run(item.getContent());
                }
                if(!find)
                {
                    int i = -1;
                    String prestr = "",laststr = "";
                    System.out.println("The word "+command[1]+" is not in the ordered dictionary");
                    List<String> data = orderedDict.getNames();
                    if(data.size() > 0)
                    {
                        for(i = 0; i < data.size(); ++i)
                        {
                            if(command[1].compareTo(data.get(i)) < 0)
                            {
                                break;
                            }
                        }
                        if(i == 0)
                        {
                            laststr = data.get(0);
                        }
                        else if(i == data.size())
                        {
                            prestr = data.get(i-1);
                        }
                        else
                        {
                            prestr = data.get(i-1);
                            laststr = data.get(i);
                        }
                    }
                    System.out.println("Preceding word: "+prestr);
                    System.out.println("Following word: "+laststr);
                }
            }
            else if(command[0].compareTo("remove") == 0 && command.length == 3)
            {
                Key k = new Key(command[1],command[2]);
                try
                {
                    orderedDict.remove(k);
                }
                catch (DictionaryException de)
                {
                    System.out.println("No record in the ordered dictionary has key ("+k.getName()+","+k.getKind()+").");
                }
            }
            else if(command[0].compareTo("add") == 0 && command.length == 4)
            {
                Key k = new Key(command[1],command[2]);
                DataItem item = new DataItem(k,command[3]);
                try
                {
                    orderedDict.put(item);
                }
                catch (DictionaryException de)
                {
                    System.out.println("A record with the given key ("+k.getName()+","+k.getKind()+") is already in the ordered dictionary.");
                }
            }
            else if(command[0].compareTo("list") == 0 && command.length == 2)
            {
                List<String> data = orderedDict.getNames();
                String prename = "";
                for(int i = 0; i < data.size(); ++i)
                {
                    if(data.get(i).startsWith(command[1]))
                    {
                        prename += data.get(i);
                        prename += ", ";
                    }
                }
                if(prename.length() > 0)
                {
                    System.out.println(prename.substring(0,prename.length()-2));
                }
                else
                {
                    System.out.println("No name attributes in the ordered dictionary start with prefix "+command[1]);
                }
            }
            else if(command[0].compareTo("first") == 0 && command.length == 1)
            {
                DataItem item = orderedDict.smallest();
                if(item != null)
                {
                    System.out.println(item.getKey().getName()+","+item.getKey().getKind()+","+item.getContent());
                }
            }
            else if(command[0].compareTo("last") == 0 && command.length == 1)
            {
                DataItem item = orderedDict.largest();
                if(item != null)
                {
                    System.out.println(item.getKey().getName()+","+item.getKey().getKind()+","+item.getContent());
                }
            }
            else if(command[0].compareTo("end") == 0 && command.length == 1)
            {
                break;
            }
            else
            {
                System.out.println("Invalid Command,try again!");
            }
        }
    }
}
