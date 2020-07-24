public class Player {

    public enum AttackStyle {
        ACCURATE,
        CONTROLLED,
        DEFENSIVE,
        AGGRESSIVE;
    }
    //Your stats
    int attackLevel;                          //Your attack level
    int strengthLevel;                          //Your Strength level
    int defenseLevel;                          //Your defense level
    int originalHealthLevel;                       //Your Hit points level
    int healthLevel;
    AttackStyle style;
    Weapon weapon;

    public Player(int attackLevel, int strengthLevel,int defenseLevel, int originalHealthLevel, AttackStyle style,Weapon weapon){
        this.attackLevel =  attackLevel;
        this.strengthLevel = strengthLevel;
        this.defenseLevel = defenseLevel;
        this.originalHealthLevel = originalHealthLevel;
        this.healthLevel = originalHealthLevel;
        this.style = style;
        this.weapon = weapon;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public int getStrengthLevel() {
        return strengthLevel;
    }

    public void setStrengthLevel(int strengthLevel) {
        this.strengthLevel = strengthLevel;
    }

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

    public int getOriginalHealthLevel() {
        return originalHealthLevel;
    }

    public void setOriginalHealthLevel(int originalHealthLevel) {
        this.originalHealthLevel = originalHealthLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public int getAttackStyleATK() {

        switch (style){
            case ACCURATE:
                return 3;
            case DEFENSIVE:
                return 0;
            case CONTROLLED:
                return 1;
            case AGGRESSIVE:
                return 0;
            default:
                return -1;
        }
    }

    public int getAttackStyleSTR() {
        switch (style){
            case ACCURATE:
                return 0;
            case DEFENSIVE:
                return 0;
            case CONTROLLED:
                return 1;
            case AGGRESSIVE:
                return 3;
            default:
                return -1;
        }
    }


    public int getAttackStyleDEF() {
        switch (style){
            case ACCURATE:
                return 0;
            case DEFENSIVE:
                return 3;
            case CONTROLLED:
                return 1;
            case AGGRESSIVE:
                return 0;
            default:
                return -1;
        }
    }


    public AttackStyle getStyle() {
        return style;
    }

    public void setStyle(AttackStyle style) {
        this.style = style;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void hit(int damage){
        healthLevel -= damage;
    }
    public void resetHealth(){
        healthLevel = originalHealthLevel;
    }
}
