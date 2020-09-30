import java.net.URL;
import java.io.IOException;
import java.util.Scanner;

public class URLLoader {

	int[][] holderArray;
	int x, y;
	String design;

	
	public URLLoader() {
		// TODO Auto-generated constructor stub
		webLoader("frothingpuffer");
	}
	
	public void webLoader(String d) {

		/*
		 * this method will bring in a design from a run length encoded file on the Internet and put it
		 * in a 2D array to be placed in the world
		 */
		
		design = d;

		try {
			URL url = new URL("http://www.conwaylife.com/patterns/"+design+".rle");
			Scanner scanner = new Scanner(url.openStream());
			
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
						}

						if (token.startsWith("y")) {
							String[] stringY = token.split("y = ");
							y = Integer.parseInt(stringY[1]);
						}

					}
					
					holderArray = new int[y][x];
					

				}

				else {
					
					
//					for (int i = 0; i < x; i++) {
//						  for (int j = 0; j < x; j++) {
//						    holderArray[i][j] = 0;
//						  }
//						}
					
					
					for (int l = 0; l < nextline.length(); l++) {
						int num;
						char charry = nextline.charAt(l);

						if ('b' == charry) {
							System.out.print(".");
						} else if ('o' == charry) {
							System.out.print("o");
						} else if ('$' == charry) {
							System.out.print("\n");
						} else if (Character.isDigit(charry)) {
							num = charry - '0';
							l++;
							charry = nextline.charAt(l);
							if ('b' == charry) {
								for (int i = 0; i < num; i++) {
									System.out.print(".");
								}
							} else if ('o' == charry) {
								for (int i = 0; i < num; i++) {
									System.out.print("o");
								}
							} else if ('$' == charry) {
								for (int i = 0; i < num; i++) {
									System.out.print("\n");
								}
							}

						}
					}

				}

			}

			scanner.close();
		} catch (

		IOException ex) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new URLLoader();
	}

}
