
public class PartBClass {
	// Part B : Variable bar heights but fixed block heights
		// Tested Scenarios with barHeights: 123467, 123487, 111511, 111111, 268862,
		// 444444
		public static void PartB(int barHeights[], Robot r) {
			int targetBlockHeight = 0, sourceBlockHeight = 8, maxBarHeight = 0, h = 0, w = 8, blockHeight = 2;
			// finding the maximum bar height
			for (int i = 0; i < barHeights.length; i++)
				maxBarHeight = (barHeights[i] > maxBarHeight) ? barHeights[i] : maxBarHeight;
			int i, j;
			// calculating the initial height of the robot
			h = (sourceBlockHeight > maxBarHeight && sourceBlockHeight - maxBarHeight > 1) ? sourceBlockHeight - 1
					: maxBarHeight + 1;
			for (i = 0; i < h; i++)
				r.up();
			for (i = 0; i < 4; i++) {
				for (j = 0; j <= w; j++)
					r.extend();
				for (j = h; j > sourceBlockHeight - 1; j--)
					r.lower();
				r.pick(); // picking the block from the source
				for (j = h; j > sourceBlockHeight - 1; j--)
					r.raise();
				sourceBlockHeight -= blockHeight;
				for (j = 0; j <= w; j++)
					r.contract();
				for (j = h; j > targetBlockHeight + 1; j--)
					r.lower();
				r.drop(); // dropping the block at the target
				targetBlockHeight += blockHeight;
				for (j = h; j > targetBlockHeight - 1; j--)
					r.raise();
				// changing the height of the robot accordingly.
				if ((sourceBlockHeight == targetBlockHeight && h == targetBlockHeight)
						|| (h == targetBlockHeight && targetBlockHeight != 8)
						|| (h - targetBlockHeight < 1 && targetBlockHeight != 8)) {
					h += blockHeight;
					for (j = 0; j < blockHeight; j++)
						r.up();
				}
				if (sourceBlockHeight < h && maxBarHeight + blockHeight < h && targetBlockHeight != h
						&& h - targetBlockHeight > blockHeight && h - targetBlockHeight - 2 >= 2) {
					int oldh = h;
					h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight - 1 : maxBarHeight + blockHeight;
					for (j = oldh; j > h; j--)
						r.down();
				}
			}
		}
}
