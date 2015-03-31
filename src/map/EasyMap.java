package map;

import java.util.ArrayList;

import javax.swing.JComponent;

public class EasyMap implements IMap{
	
	private int Width;
	private int Height;
	public Cell[][] Grid; // 2 dimensional array
	public Cell EntryCell;
	public Cell ExitCell;
	public ArrayList<Cell> path;

	public EasyMap(int w, int h){
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();
		
		createGeneric();
	}
	
	public void setEntryCell(Cell aCell){
		EntryCell = aCell;
	}
	@Override
	public Cell getEntryCell() {
		return EntryCell;
	}

	@Override
	public Cell getExitCell() {
		return ExitCell;
	}	
	public void setExitCell(Cell aCell){
		ExitCell = aCell;
	}
	
	public void addGridCell(Cell aCell){
		int i = aCell.getX();
		int j = aCell.getY();
		Grid[i][j] = aCell;
	}
	
	public void addPathCell(Cell aCell){
		path.add(aCell);
	}

	@Override
	public ArrayList<Cell> getPath() {
		return path;
	}
	@Override
	public JComponent getComponent(int x, int y) {
		return Grid[x][y].getComponent();
	}

	@Override
	public int getMapWidth() {
		return Width;
	}

	@Override
	public int getMapHeight() {
		return Height;
	}
	public boolean validate(){
		boolean valid = true;
		
		//check if the grid completely filled with cells
		for(int i=0; i<Width; i++){
			for(int j=0; j<Height; j++){				
				if(Grid[i][j] == null){
					valid = false;
				} else {
					Grid[i][j].printType();
					System.out.print(" ");	
				}
			}
			System.out.print("\n");
		}
		
		//check if path is continuous and does not branch out
		int previousX = -1;
		int previousY = -1;
		Cell FirstCell;
		Cell LastCell;
		
		if(EntryCell == null || ExitCell == null){
			valid = false;
		} else {
			FirstCell = EntryCell;
			LastCell = ExitCell;
			previousX = FirstCell.getX();
			previousY = FirstCell.getY();
		//see if at least one side the current cell is connected to the previous cell
		//if path has branches,the cell at the end of the branch won't be connected
		//to the next cell, so return false
			for(Cell aCell : path){
				if(aCell.getX() != previousX && aCell.getY() != previousY ){
					valid = false;
					break;
				}
				previousX = aCell.getX();
				previousY = aCell.getY();
			}
		//at the end of the for loop, previousX and Y should be the coordinates of
		//the last cell.
		//check if it is the coordinate of the last cell
			if(previousX != LastCell.getX() || previousY != LastCell.getY()){
				valid = false;
			}
		}
		
		return valid;
	}
	
	private void sortPathCells() {
		
		ArrayList<Cell> path = new ArrayList<>();
		Cell lastCell = null;
		Cell currentCell;
		Cell[] nextCell = new Cell[4];
		
		currentCell = EntryCell;
		path.add(EntryCell);
		
		while(currentCell != null) {
			
			nextCell[0] = Grid[boundNumber(currentCell.position.x - 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid.length - 1)];
			nextCell[1] = Grid[boundNumber(currentCell.position.x + 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid.length - 1)];
			nextCell[2] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y - 1, 0, Grid.length - 1)];
			nextCell[3] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y + 1, 0, Grid.length - 1)];
			
			for (int i = 0; i < nextCell.length; i++) {
				if (nextCell[i] != currentCell && nextCell[i] != lastCell && nextCell[i] instanceof PathCell) {
					lastCell = currentCell;
					currentCell = nextCell[i];
					path.add(currentCell);
					break;
				}
				if (i == 3) {
					currentCell = null;
				}
			}
		}
		
		this.path = path;
		
		
	}
	
	private int boundNumber(int number, int lowerBound, int upperBound) {
		if (number < lowerBound) {
			return lowerBound;
		}
		else if (number > upperBound) {
			return upperBound;
		}
		else {
			return number;
		}
	}
	
	public void createGeneric() {

				//FixedMap MyMap = new FixedMap(mapWidth, mapHeight);
				Cell aCell;
				
				//set every cell to SceneryCell first
				for(int i=0; i<Width; i++){
					for(int j=0; j<Height; j++){
						aCell = new SceneryCell(i,j);
						this.addGridCell(aCell);
					}
				}
				
				// user set the path
				// set the selected cells to PathCell
				// set the EntryCell chosen by user to EntryCell
				aCell = new PathCell(0,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
				
				aCell = new PathCell(1,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				//
				aCell = new PathCell(2,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(3,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(4,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(6,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(7,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(9,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(10,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				aCell = new PathCell(12,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(14,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				// set this ExitCell chosen by user to ExitCell
				this.setExitCell(aCell);
				
				sortPathCells();
	}
}