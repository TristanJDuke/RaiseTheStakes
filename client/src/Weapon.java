public class Weapon {

    enum Type{
        ABYSSAL_TENTACLE,
        ZAMORAKIAN_HASTA,
        ABYSSAL_WHIP,
        DRAGON_SCIMITAR

    }

    //Abyssal tentacle, atk bonus: 90 (accurate,controlled, defensive), 86 str,
    //Zamorakian hasta, atk bonus: 85(controlled), 65(controlled, defensive), 65(controlled), 75 str
    //Abyssal whip,  atk bonus: 82 (accurate,controlled, defensive), 82 str,
    //Dragon scimitar, atk: bonus 67(accurate, aggressive, defensive), 8(controlled), 66 str

    private int weaponAttackBonus;
    private int weaponStrengthBonus;
    private int weaponDefenceBonus;
    private Type name;

    public Weapon(int weaponAttackBonus,int weaponStrengthBonus,int weaponDefenceBonus,Type name)
    {
        this.weaponAttackBonus = weaponAttackBonus;
        this.weaponStrengthBonus = weaponStrengthBonus;
        this.weaponDefenceBonus =  weaponDefenceBonus;
        this.name = name;
    }

    public int getWeaponAttackBonus() {
        return weaponAttackBonus;
    }

    public void setWeaponAttackBonus(int weaponAttackBonus) {
        this.weaponAttackBonus = weaponAttackBonus;
    }

    public int getWeaponStrengthBonus() {
        return weaponStrengthBonus;
    }

    public void setWeaponStrengthBonus(int weaponStrengthBonus) {
        this.weaponStrengthBonus = weaponStrengthBonus;
    }

    public int getWeaponDefenceBonus() {
        return weaponDefenceBonus;
    }

    public void setWeaponDefenceBonus(int weaponDefenceBonus) {
        this.weaponDefenceBonus = weaponDefenceBonus;
    }

    public Type getName() {
        return name;
    }

    public void setName(Type name) {
        this.name = name;
    }

}

