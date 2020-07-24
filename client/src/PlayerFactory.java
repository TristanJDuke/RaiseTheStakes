import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PlayerFactory {

    static boolean DEBUG = false;

    public static Player getPlayer(String name) {
        if (name == null) {
            return null;
        } else {
            String url = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + name;

            URL target = null;
            try {
                target = new URL(url);
                if (DEBUG) {
                    System.out.println("Fetching\"" + target + "\"");
                }
            } catch (MalformedURLException e) {
                if (DEBUG) {
                    System.out.println("Bad URL \"" + url + "\":"
                            + e.getMessage());
                }
                return null;
            } catch (SecurityException e) {
                if (DEBUG) {
                    System.out.println("Security Error: " + e.getMessage());
                }
                return null;
            }
            String content = "";
            try {
                URLConnection con = target.openConnection();
                byte b[] = new byte[1024];
                int nbytes;
                BufferedInputStream in =
                        new BufferedInputStream(con.getInputStream(), 2048);
                while ((nbytes = in.read(b, 0, 1024)) != -1) {
                    content += new String(b, 0, nbytes, "UTF-8");
                }
                if (DEBUG) {
                    System.out.println(con.getContentType());
                }


                int size = con.getContentLength();
                if (size == -1) {
                    size = content.length();
                }
                if (DEBUG) {
                    System.out.println("Content Size: " + size);
                    System.out.println("Contents: " + content);
                }
            } catch (IOException e) {
                if (DEBUG) {
                    System.out.println("Error fetching \"" + target + "\": " + e);
                }
                return null;
            }
            String[] dataSets = content.split("\n");
            String[] attackStats = dataSets[1].split(",");
            int attackLevel = Integer.parseInt(attackStats[1]);
            String[] defenseStats = dataSets[2].split(",");
            int defenseLevel = Integer.parseInt(defenseStats[1]);
            String[] strengthStats = dataSets[3].split(",");
            int strengthLevel = Integer.parseInt(strengthStats[1]);
            String[] healthStats = dataSets[4].split(",");
            int healthLevel = Integer.parseInt(healthStats[1]);


            return new Player(attackLevel, strengthLevel, defenseLevel, healthLevel, Player.AttackStyle.CONTROLLED,
                    WeaponFactory.getWeapon(Weapon.Type.ABYSSAL_TENTACLE));
        }
    }
}