package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

public class Main {

	public static void main(String[] args) {
		// file path can give Run configuration tab -> arguments ex c:\file.txt
		if (args.length > 0) {
			try {
				System.out.println(Packer.pack(args[0]));
			} catch (APIException e) {
				e.printStackTrace();
			}
		} else {
			// there is no args in the list prompt a message
			System.err.println("Please, enter a filepath.");
		}
	}

}