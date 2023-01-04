public class MedievalLevelBuilder {
  private final Level level;
  private final int numRoom;
  private int currRoom, numMonster, numTreasure;

  public MedievalLevelBuilder(int levelNumber, int numRoom, int numMonster, int numTreasure) {
    if (levelNumber < 0 || numRoom < 0 || numMonster < 0 || numTreasure < 0)
      throw new IllegalArgumentException("Input shouldn't be negative!");
    this.numRoom = numRoom;
    this.numMonster = numMonster;
    this.numTreasure = numTreasure;
    level = new Level(levelNumber);
  }

  public MedievalLevelBuilder addRoom(String description) {
    if (numRoom == currRoom)
      throw new IllegalStateException("Too many rooms are added to the level!");
    level.addRoom(description);
    currRoom++;
    return this;
  }

  public MedievalLevelBuilder addGoblins(int roomNumber, int numGoblins) {
    validateMonster(numGoblins);
    validateRoom(roomNumber);
    for (int i = 0; i < numGoblins; i++)
      level.addMonster(roomNumber, new Monster("goblin", "mischievous and very " +
              "unpleasant, vengeful, and greedy creature whose primary purpose is to cause trouble " +
              "to humankind", 7));
    numMonster -= numGoblins;
    return this;
  }

  public MedievalLevelBuilder addOrc(int roomNumber) {
    validateMonster(1);
    validateRoom(roomNumber);
    level.addMonster(roomNumber, new Monster("orc", "brutish, aggressive, malevolent" +
            " being serving evil", 20));
    numMonster--;
    return this;
  }

  public MedievalLevelBuilder addOgre(int roomNumber) {
    validateMonster(1);
    validateRoom(roomNumber);
    level.addMonster(roomNumber, new Monster("ogre", "large, hideous man-like being " +
            "that likes to eat humans for lunch", 50));
    numMonster--;
    return this;
  }

  public MedievalLevelBuilder addHuman(int roomNumber, String description) {
    validateMonster(1);
    validateRoom(roomNumber);
    level.addMonster(roomNumber, new Monster("human", description, 0));
    numMonster--;
    return this;
  }

  public MedievalLevelBuilder addPotion(int roomNumber) {
    validateTreasure();
    validateRoom(roomNumber);
    level.addTreasure(roomNumber, new Treasure("a healing potion", 1));
    numTreasure--;
    return this;
  }

  public MedievalLevelBuilder addGold(int roomNumber, int value) {
    validateTreasure();
    validateRoom(roomNumber);
    level.addTreasure(roomNumber, new Treasure("pieces of gold", value));
    numTreasure--;
    return this;
  }

  public MedievalLevelBuilder addWeapon(int roomNumber, String description) {
    validateTreasure();
    validateRoom(roomNumber);
    level.addTreasure(roomNumber, new Treasure(description, 10));
    numTreasure--;
    return this;
  }

  public MedievalLevelBuilder addSpecial(int roomNumber, String description, int value) {
    validateTreasure();
    validateRoom(roomNumber);
    level.addTreasure(roomNumber, new Treasure(description, value));
    numTreasure--;
    return this;
  }

  public Level build() {
    return this.level;
  }

  private void validateMonster(int currMonster) {
    if (numMonster < currMonster)
      throw new IllegalStateException("Too many monsters are added to the level!");
  }

  private void validateTreasure() {
    if (numTreasure == 0)
      throw new IllegalStateException("Too many treasures are added to the level!");
  }

  private void validateRoom(int roomNumber) {
    if (roomNumber >= currRoom)
      throw new IllegalArgumentException("The target room has not yet been created!");
  }
}
