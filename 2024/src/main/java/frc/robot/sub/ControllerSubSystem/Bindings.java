package frc.robot.sub;

import frc.robot.data.ButtonMap;

public class Bindings {
	static final pressed = 1;
	static final held = 2;
	// to get if a button is pressed (controllerState[index] & pressed != 0)

	XboxController xboxController;
	int[] xboxControllerProfile = {
		 1, //A
		 2, //B
		 3, //X
		 4, //Y
		 5, //LB
		 6, //RB
		 7, //BACK
		 8, //START
		 9, //LSTICKPUSH
		-5, //LSTICKX
		-6, //LSTICKY
		10, //RSTICKPUSH
		-7, //RSTICKX
		-8  //RSTICKY
	};

	xboxController flightStickController;
	int[] flightStickControllerProfile = {
		 1, //BUTTON 1
		 2, //BUTTON 2
		 3, //BUTTON 3
		 4, //BUTTON 4
		 5, //BUTTON 5
		 6, //BUTTON 6
		 7, //BUTTON 7
		 8, //BUTTON 8
		 9, //BUTTON 9
		10, //BUTTON 10
		11, //BUTTON 11
		12, //BUTTON 12
		-1, //ROLL
		-2, //PITCH
		-3, //YAW
		-4  //SLIDER
	};

	int[] controllerState = new int[30];

	private void updateControllerState(int[] profile, int index, XboxController controller) {
		for (int i = 0; i < profile.length; i++) {
			if (profile[i] < 0) { // axis
				controllerState[i + index] = controller.getRawAxis(profile[i]);
			} else {              // button
				if (controller.getRawButton(profile[i])) {
					if (controllerState[i + index] == 0) {
						controllerState[i + index] = 3; // bin (11)
					} else {
						controllerState[i + index] = 2; // bin (10)
					}
				} else {
					controllerState[i + index] = 0;
				}
			}
		}
	}
}