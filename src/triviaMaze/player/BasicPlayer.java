package triviaMaze.player;

import triviaMaze.room.IRoom;
import triviaMaze.keyService.*;

public class BasicPlayer implements IPlayer
{
	private IRoom cur;
	private IRoom end;
	
	public BasicPlayer(IRoom start, IRoom end) {
		this.end = end;
		cur = start;
		addKeyHandlers();
	}
	
	private void addKeyHandlers() {
		TmKeyService.addHandler(new KeyHandler('a'){
			private String direction = "left";
			@Override
			public void fire() {
				if (cur.isEnabled(direction)) {
					cur = cur.getRoom(direction);
					System.out.println("Moved " + direction);
				}
			}
		});
		TmKeyService.addHandler(new KeyHandler('d'){
			private String direction = "right";
			@Override
			public void fire() {
				if (cur.isEnabled(direction)) {
					cur = cur.getRoom(direction);
					System.out.println("Moved " + direction);
				}
			}
		});
		TmKeyService.addHandler(new KeyHandler('w'){
			private String direction = "up";
			@Override
			public void fire() {
				if (cur.isEnabled(direction)) {
					cur = cur.getRoom(direction);
					System.out.println("Moved " + direction);
				}
			}
		});
		TmKeyService.addHandler(new KeyHandler('s'){
			private String direction = "down";
			@Override
			public void fire() {
				if (cur.isEnabled(direction)) {
					cur = cur.getRoom(direction);
					System.out.println("Moved " + direction);
				}
			}
		});
		TmKeyService.addHandler(new KeyHandler(' '){
			@Override
			public void fire() {
				System.out.println("At exit? " + (cur.equals(end)));
			}
		});
	}

	@Override
	public IRoom getRoom()
	{
		return cur;
	}

	@Override
	public void move(IRoom to)
	{
		cur = to;
	}

}
