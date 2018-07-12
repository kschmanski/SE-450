package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.DrawEllipseCommand;
import main.DrawRectangleCommand;
import main.DrawTriangleCommand;
import main.ICommand;
import model.persistence.ApplicationState;

public class MouseHandler extends MouseAdapter  {
	
	ApplicationState a;
	Pair starting;
	Pair ending;
	
	public MouseHandler(ApplicationState a) {
		this.a = a;
	};
	
	@Override
	public void mousePressed(MouseEvent e) {
		int starting_x = e.getX();
		int starting_y = e.getY();
		Pair starting = new Pair(starting_x, starting_y);
		System.out.printf("mouse clicked at %d, %d\n", starting.getX(), starting.getY());
	
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		ICommand command;
		int ending_x = e.getX();
		int ending_y = e.getY();
		Pair ending = new Pair(ending_x, ending_y);
		System.out.printf("mouse released at %d, %d\n", ending.getX(), ending.getY());
		//now create a new shape
		switch(a.getActiveShapeType().toString()) {
		
		case "TRIANGLE":
			command = new DrawTriangleCommand(starting, ending);
			break;
		case "RECTANGLE":
			command = new DrawRectangleCommand(starting, ending);
			break;
		case "ELLIPSE":
			command = new DrawEllipseCommand(starting, ending);
			break;
		default:
			throw new Error();

		}
		command.run();
	}
	
	
	
	
	}

//drawshape command -> then run it w/ X,Y coords
//check StartandEndPointMode on MouseRelease - if select, create a select command.
//if move, create move command. etc.  But for now, just create a createshape command
//CreateShape command calls Run -> creates a shape that contains the start point and end point,
//primary color, secondary color, and the shading type.
//Use strategy pattern to show how we'll draw the shape
//add this newly created shape to the ShapeList (ShapeList could use Observer pattern)
