package src;

import ch.aplu.jgamegrid.Location;

public class PacManGameGridAdapter extends src.PacManGameGrid
{
  private int nbHorzCells;
  private int nbVertCells;
  private int[][] mazeArray;

  public PacManGameGridAdapter(int nbHorzCells, int nbVertCells, String mapString)
  {
    super(nbHorzCells, nbVertCells);
    this.nbHorzCells = nbHorzCells;
    this.nbVertCells = nbVertCells;
    mazeArray = new int[nbVertCells][nbHorzCells];

    // Copy structure into integer array
    for (int i = 0; i < nbVertCells; i++)
    {
      for (int k = 0; k < nbHorzCells; k++) {
        int value = toInt(mapString.charAt(nbHorzCells * i + k));
        mazeArray[i][k] = value;
      }
    }
  }

  public int getCell(Location location)
  {
    return mazeArray[location.y][location.x];
  }
  private int toInt(char c)
  {
    if (c == 'b') // wall
      return 0;
    if (c == 'c') // pill
      return 1;
    if (c == 'a')
      return 2;
    if (c == 'f')
      return 2;
    if (c == 'g')
      return 2;
    if (c == 'h')
      return 2;
    if (c == 'i')
      return 5;
    if (c == 'j')
      return 6;
    if (c == 'k')
      return 7;
    if (c == 'l')
      return 8;
    if (c == 'd') // gold
      return 3;
    if (c == 'e') // ice
      return 4;
    return -1;
  }
}
