
public class PartCClass {
	// PartC : Variable blockheights and barheights
		public static void PartC(int[] barHeights, int[] blockHeights, Robot r) {
			int targetBlockHeight = 0, sourceBlockHeight = 0, maxBarHeight = 0, h = 0, w = 8;
			int i, j;
			// finding the maximum bar height
			for (i = 0; i < barHeights.length; i++)
				maxBarHeight = (barHeights[i] > maxBarHeight) ? barHeights[i] : maxBarHeight;
			// finding the source blockheight
			for (i = 0; i < blockHeights.length; i++)
				sourceBlockHeight += blockHeights[i];
			// calculating the initial height of the robot
			if (sourceBlockHeight > maxBarHeight && sourceBlockHeight - maxBarHeight >= blockHeights[3]) {
				h = sourceBlockHeight - 1;
			} else {
				h = maxBarHeight + blockHeights[3] - 1;
			}
			for (i = 0; i < h; i++)
				r.up();
			for (i = blockHeights.length - 1; i >= 0; i--) {
				for (j = 0; j <= w; j++)
					r.extend(); // extending the block till source
				for (j = h; j > sourceBlockHeight - 1; j--)
					r.lower();
				r.pick(); // picking the block from the source
				for (j = h; j > sourceBlockHeight - 1; j--)
					r.raise(); // raising till the arm height
				sourceBlockHeight -= blockHeights[i];
				for (j = 0; j <= w; j++)
					r.contract(); // contracting the block till target
				for (j = h; j > targetBlockHeight + blockHeights[i] - 1; j--)
					r.lower();
				r.drop(); // dropping the block at the target
				targetBlockHeight += blockHeights[i];
				for (j = h; j > targetBlockHeight - 1; j--)
					r.raise();
				// changing the height of the robot according to the source height
				// of the blocks.
				if (i > 0) {
					if (h - targetBlockHeight - blockHeights[i - 1] + 1 > blockHeights[i - 1]
							&& h - maxBarHeight + 1 > blockHeights[i - 1]) {
						int oldh = h + 1;
						if (sourceBlockHeight > maxBarHeight) {
							if (sourceBlockHeight - maxBarHeight >= blockHeights[i]) {
								h = sourceBlockHeight - 1;
								for (j = oldh; j > h + 1; j--)
									r.down();
							} else {
								h = maxBarHeight + blockHeights[i - 1];
								for (j = oldh; j > h; j--)
									r.down();
								h--;
							}

						} else if (sourceBlockHeight <= maxBarHeight) {
							h = maxBarHeight + blockHeights[i - 1];
							for (j = oldh; j > h; j--)
								r.down();
							h--;
						}

					} else if ((h - targetBlockHeight + 1 < blockHeights[i - 1])) {
						int oldh = h;
						h += blockHeights[i - 1] - (h - targetBlockHeight + 1);
						for (j = oldh; j < h; j++)
							r.up();
					} else if (h - maxBarHeight + 1 < blockHeights[i - 1]) {
						int oldh = h;
						h += blockHeights[i - 1] - (h - maxBarHeight + 1);
						for (j = oldh; j < h; j++)
							r.up();
					}
				}
			}
		}

		
}
