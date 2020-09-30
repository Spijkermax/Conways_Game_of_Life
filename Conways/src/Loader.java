
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Loader {

	boolean[][] holderArray;
	int x, y;
	String pattern;
	String type;
	Scanner scanner;
	int posx = 0;
	int posy = 0;
	CellWorld cw;

	public Loader() {
		//webFileLoader("pufferfishbreeder", "web");
		webFileLoader("pulsar", "web");
		
	}

	public void webFileLoader(String p, String t) {

		/*
		 * this method will bring in a design from a run length encoded file on the
		 * Internet and put it in a 2D array to be placed in the world
		 */

		pattern = p;
		type = t;

		try {

			/*
	         * AMF Loader
			 * this will bring in a design from a run length encoded file on the Internet.
			 */
			
			if ("web".equals(type)) {
				URL url = new URL("http://www.conwaylife.com/patterns/" + pattern + ".rle");
				scanner = new Scanner(url.openStream());
			}
			
			/*
	         * AMF Loader
			 * this will bring in a design from a run length encoded file.
			 */

			else if ("file".equals(type)) {
				File patternFile = new File(pattern + ".rle.txt");
				scanner = new Scanner(patternFile);
			}

			while (scanner.hasNext()) {
				String nextline = scanner.nextLine();
				if (nextline.startsWith("#")) {
					/* do nothing, skip the comments */
				}

				else if (nextline.startsWith("x")) {
					/* get the size and make the 2D array */

					String sections[] = nextline.split(", ");
					for (String token : sections) {
						if (token.startsWith("x")) {
							String[] stringX = token.split("x = ");
							x = Integer.parseInt(stringX[1]);
//							System.out.println(x);
						}

						if (token.startsWith("y")) {
							String[] stringY = token.split("y = ");
							y = Integer.parseInt(stringY[1]);
//							System.out.println(x);
						}

					}

					cw = new CellWorld(x, y);
					

				}

				else {

					 /* Check each character in the lines containing the data */
					
					for (int l = 0; l < nextline.length(); l++) {
					
							int num;
							char charry = nextline.charAt(l);

							if ('b' == charry) {
								/* b is a dead cell */
//								System.out.print(".");
								posx++;
							} else if ('o' == charry) {
								/* o is alive cell */
//								System.out.print("o");
								cw.getCell(posx, posy).swapState();
								posx++;
							} else if ('$' == charry) {
								/* new row */
//								System.out.print("\n");
								posx = 0;
								posy++;
							} else if (Character.isDigit(charry)) {
								/* a digit indicates the next character is repeated */
								num = charry - '0';
								l++;
								charry = nextline.charAt(l);
								if ('b' == charry) {
									/* dead cells */
									for (int k = 0; k < num; k++) {
//										System.out.print(".");
										posx++;
									}
								} else if ('o' == charry) {
									/* alive cells */
									for (int k = 0; k < num; k++) {
//										System.out.print("o");
										cw.getCell(posx, posy).swapState();
										posx++;
									}
								} else if ('$' == charry) {
									/* new row */
									for (int k = 0; k < num; k++) {
//										System.out.print("\n");
										posx = 0;
										posy++;
									}

								}
							}

						}
					}

				}

			scanner.close();
			
			/* comment this out eventually, it is just a visual representation of the array */
			
			System.out.print("From the array:\n");

			for (int i = 0; i < cw.getWorld().length; i++) {
				for (int j = 0; j < cw.getWorld()[i].length; j++) {
					String sym;
					if (cw.getWorld()[i][j].isAlive() == true) {
						sym = "o";
					} else {
						sym = ".";
					}
					System.out.print(sym);

				}
				System.out.print("\n");
			}

		} catch (

		IOException ex) {

		}
	}
	
	public CellWorld getCellWorld() {
		return cw;
	}

	/* uncomment the main method to run standalone */
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Loader();
//	}

}

