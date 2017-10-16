package group.csc280.pente.model;

public enum Direction {
	left(-1, 0),
	right(1, 0),
	down(0, -1),
	up(0, 1),
	upright(1, 1),
	upleft(-1, 1),
	downleft(-1, -1),
	downright(1, -1);
	
	public int dx;
	public int dy;

	Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
