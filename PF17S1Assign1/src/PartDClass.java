
public class PartDClass {

	// keeping the blocks on the target according to required order
	public static void PartD(int[] barHeights, int[] blockHeights, int[] required, Robot r) {
		int i, pos, requiredBlockNo = 0, sourceHeight = 0, tempHeight = 0, targetHeight = 0, h = 1, w = 0,
				requiredBlock = 0, found = 0, maxBarHeight = 0;
		int[] tempHeights = new int[tempHeight];

		// finding the maximum bar height
		for (i = 0; i < barHeights.length; i++)
			maxBarHeight = (barHeights[i] > maxBarHeight) ? barHeights[i] : maxBarHeight;
		int totalNoOfblocks = blockHeights.length;
		// the loop will run until all the blocks are placed on target
		while (requiredBlockNo < totalNoOfblocks) {
			pos = -1;
			// searching for the required block
			for (i = blockHeights.length - 1; i >= 0; i--) {
				pos++;
				if (blockHeights[i] == required[requiredBlockNo]) {
					w = 8;
					requiredBlock = blockHeights[i];
					found = 1;
					break;
				}

			}
			// if block is found at source and is on the top
			if (found == 1 && pos == 0) {
				sourceHeight = height(blockHeights);
				h = findHeight(tempHeight, sourceHeight, targetHeight, requiredBlock, maxBarHeight, h, 0, r);
				for (i = 0; i <= w; i++)
					r.extend();
				for (i = h; i > sourceHeight; i--)
					r.lower();
				r.pick();
				blockHeights = removeElements(blockHeights);
				for (i = h; i > sourceHeight; i--)
					r.raise();
				for (i = 0; i <= w; i++)
					r.contract();
				for (i = h; i > targetHeight + requiredBlock; i--)
					r.lower();
				r.drop();
				for (i = h; i > targetHeight + requiredBlock; i--)
					r.raise();
				targetHeight += requiredBlock;
				found = 0;
			}
			// if block is found at source and is not on the top
			else if (found == 1 && pos != 0) {
				int j;
				// keep element on temp
				for (i = 0; i < pos; i++) {
					sourceHeight = height(blockHeights);
					tempHeight = height(tempHeights);
					h = findHeight(tempHeight, sourceHeight, targetHeight, blockHeights[blockHeights.length - 1],
							maxBarHeight, h, 0, r);
					if (i == 0)
						for (j = 0; j <= w; j++)
							r.extend();
					else
						for (j = 0; j < 1; j++)
							r.extend();
					for (j = h; j > sourceHeight; j--)
						r.lower();
					r.pick();
					int element = blockHeights[blockHeights.length - 1];
					blockHeights = removeElements(blockHeights);
					for (j = h; j > sourceHeight; j--)
						r.raise();
					for (j = w; j > w - 1; j--)
						r.contract();
					for (j = h; j > tempHeight + element; j--)
						r.lower();
					r.drop();
					for (j = h; j > tempHeight + element; j--)
						r.raise();
					tempHeight += element;
					tempHeights = addElements(tempHeights, element);
					sourceHeight = height(blockHeights);
				}
				// keep element on target
				sourceHeight = height(blockHeights);
				h = findHeight(tempHeight, sourceHeight, targetHeight, requiredBlock, maxBarHeight, h, 0, r);
				for (i = w - 1; i < w; i++)
					r.extend();
				for (i = h; i > sourceHeight; i--)
					r.lower();
				r.pick();
				blockHeights = removeElements(blockHeights);
				for (i = h; i > sourceHeight; i--)
					r.raise();
				for (i = 0; i <= w; i++)
					r.contract();
				for (i = h; i > targetHeight + requiredBlock; i--)
					r.lower();
				r.drop();
				for (i = h; i > targetHeight + requiredBlock; i--)
					r.raise();
				targetHeight += requiredBlock;
				found = 0;

			} else {
				pos = -1;
				// searching for the required block on target
				for (i = tempHeights.length - 1; i >= 0; i--) {
					pos++;
					if (tempHeights[i] == required[requiredBlockNo]) {
						w = 8;
						requiredBlock = tempHeights[i];
						found = 1;
						break;
					}

				}
				// if block is found at target and is on the top
				if (found == 1 && pos == 0) {
					tempHeight = height(tempHeights);
					h = findHeight(tempHeight, sourceHeight, targetHeight, requiredBlock, maxBarHeight, h, 1, r);
					for (i = 0; i < w; i++)
						r.extend();
					for (i = h; i > tempHeight; i--)
						r.lower();
					r.pick();
					tempHeights = removeElements(tempHeights);
					for (i = h; i > tempHeight; i--)
						r.raise();
					for (i = 0; i < w; i++)
						r.contract();
					for (i = h; i > targetHeight + requiredBlock; i--)
						r.lower();
					r.drop();
					for (i = h; i > targetHeight + requiredBlock; i--)
						r.raise();
					targetHeight += requiredBlock;

					found = 0;
				}
				// if block is found at target and is not on the top
				else if (found == 1 && pos != 0) {
					int j;
					// keep element on source
					for (i = 0; i < pos; i++) {
						tempHeight = height(tempHeights);
						sourceHeight = height(blockHeights);
						h = findHeight(tempHeight, sourceHeight, targetHeight, tempHeights[tempHeights.length - i - 1],
								maxBarHeight, h, 1, r);
						if (i == 0)
							for (j = 0; j <= w - 1; j++)
								r.extend();
						for (j = h; j > tempHeight; j--)
							r.lower();
						r.pick();
						int element = tempHeights[tempHeights.length - 1];
						tempHeights = removeElements(tempHeights);
						for (j = h; j > tempHeight; j--)
							r.raise();
						for (j = w - 1; j < w; j++)
							r.extend();
						for (j = h; j > sourceHeight + element; j--)
							r.lower();
						r.drop();
						for (j = h; j > sourceHeight + element; j--)
							r.raise();
						for (j = w - 1; j < w; j++)
							r.contract();
						sourceHeight += element;
						blockHeights = addElements(blockHeights, element);
					}
					// keep element on target
					tempHeight = height(tempHeights);
					h = findHeight(tempHeight, sourceHeight, targetHeight, requiredBlock, maxBarHeight, h, 1, r);
					for (i = h; i > tempHeight; i--)
						r.lower();
					r.pick();
					tempHeights = removeElements(tempHeights);
					for (i = h; i > tempHeight; i--)
						r.raise();
					for (i = 0; i < w; i++)
						r.contract();
					for (i = h; i > targetHeight + requiredBlock; i--)
						r.lower();
					r.drop();
					for (i = h; i > targetHeight + requiredBlock; i--)
						r.raise();
					targetHeight += requiredBlock;
					found = 0;

				}

			}
			requiredBlockNo++;
		}
	}

	// changing the height of the robot accordingly
	public static int findHeight(int tempHeight, int sourceHeight, int targetHeight, int block, int maxBarHeight, int h,
			int onSourceOrTemp, Robot r) {

		// finding the new height of the robot
		int newh = Math.max(targetHeight, maxBarHeight);
		newh = Math.max(newh, tempHeight);
		newh = Math.max(newh, sourceHeight);
		if ((newh - maxBarHeight < block) || (newh - targetHeight < block)
				|| (newh - tempHeight < block) && onSourceOrTemp == 0)
			newh += block;
		if (sourceHeight > maxBarHeight && onSourceOrTemp == 0 && newh >= 14)
			newh = maxBarHeight + block;
		// adjusting the new height
		if (h > newh)
			for (int k = newh; k < h; k++)
				r.down();
		else
			for (int k = h; k < newh; k++)
				r.up();
		return newh;
	}

	// add element to source or temp
	public static int[] addElements(int[] tempHeights, int element) {

		int input2[] = new int[tempHeights.length + 1], i;
		for (i = 0; i < input2.length; i++) {
			if (tempHeights.length != 0 && i < tempHeights.length)
				input2[i] = tempHeights[i];
			else
				input2[i] = element;
		}

		return input2;

	}

	// remove elements from source or temp
	public static int[] removeElements(int[] input) {
		int input2[] = new int[input.length - 1];
		for (int i = 0; i < input.length - 1; i++) {
			input2[i] = input[i];
		}
		return input2;
	}

	// increase the sourceblockheights or tempblockheights
	public static int height(int[] blockHeights) {
		int sourceHeight = 0;
		for (int i = 0; i < blockHeights.length; i++)
			sourceHeight += blockHeights[i];
		return sourceHeight;
	}
}
