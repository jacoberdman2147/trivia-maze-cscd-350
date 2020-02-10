package triviaMaze.maze;

import triviaMaze.room.*;

public class LinkedRectangularMaze implements IMaze
{
	private IRoom start;
	private IRoom end;
	
	public LinkedRectangularMaze(int height, int width) {
		createMazeStructure(height, width); //Sets start and end
	}
	
	private void createMazeStructure(int height, int width) {
		IRoom[][] rooms = new IRoom[height + 2][width + 2]; //Creates a buffer on the outside to avoid dealing with boundary conditions
		for(int i = 1; i < height + 1; i++) {
			for (int j = 1; j < width + 1; j++) {
				rooms[i][j] = new LinkedRoom();
			}
		}
		for(int i = 1; i < height + 1; i++) {
			for (int j = 1; j < width + 1; j++) {
				assignRoomLinks(rooms, i, j);
			}
		}
		start = rooms[1][1];
		end = rooms[height][width];
	}
	
	private void assignRoomLinks(IRoom[][] rooms, int y, int x) {
		IRoom cur = rooms[y][x];
		cur.setRoomAtDirection("left", rooms[y][x - 1]);
		cur.setRoomAtDirection("right", rooms[y][x + 1]);
		cur.setRoomAtDirection("up", rooms[y - 1][x]);
		cur.setRoomAtDirection("down", rooms[y + 1][x]);
		
	}

	@Override
	public boolean isTraversable(IRoom cur) //NOT IMPLEMENTED
	{
		return true;
	}

	@Override
	public IRoom getStart()
	{
		return start;
	}

	@Override
	public IRoom getEnd()
	{
		return end;
	}
	
}
