public class WeaponFactory {

    public static Weapon getWeapon(Weapon.Type name)
    {
        if (name == null){
            return null;
        }
        if (name == Weapon.Type.ABYSSAL_TENTACLE){
            return new Weapon(90,86,0,name);
        }
        if(name == Weapon.Type.ZAMORAKIAN_HASTA){
            return new Weapon(85,75,0,name);
        }
        if(name == Weapon.Type.ABYSSAL_WHIP){
            return new Weapon(82,82,0,name);
        }
        if(name == Weapon.Type.DRAGON_SCIMITAR){
            return new Weapon(67,66,0,name);
        }

        return null;
    }
}
