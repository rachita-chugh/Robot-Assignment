
public class PartAClass {
	// Part A : Fixed bar heights and block heights (barHeights are 444444)
		public static void PartA(Robot r) {
			int targetBlockHeight = 0, sourceBlockHeight = 8, maxBarHeight = 4, h = 0, w = 8, blockHeight = 2;
			int i, j;
			// calculating the initial height of the robot
			h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight : maxBarHeight;

			for (i = 0; i < h - 1; i++)
				r.up();
			for (i = 0; i < 4; i++) {
				for (j = 0; j <= w; j++)
					r.extend();
				for (j = h; j > sourceBlockHeight; j--)
					r.lower();
				r.pick(); // picking the block from the source
				for (j = h; j > sourceBlockHeight; j--)
					r.raise();
				targetBlockHeight += blockHeight;
				sourceBlockHeight -= blockHeight;
				for (j = 0; j <= w; j++)
					r.contract();
				for (j = h; j > targetBlockHeight; j--)
					r.lower();
				r.drop(); // dropping the block at the target
				for (j = h; j > targetBlockHeight; j--)
					r.raise();
				// changing the height of the robot accordingly
				if (sourceBlockHeight < h && maxBarHeight + blockHeight < h && targetBlockHeight != h) {
					int oldh = h;
					h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight : maxBarHeight + blockHeight;
					for (j = oldh; j > h; j--)
						r.down();
				} else if (h == targetBlockHeight && targetBlockHeight != 8) {
					h += blockHeight;
					for (j = 0; j < blockHeight; j++)
						r.up();
				}
			}
		}
}
