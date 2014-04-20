package com.game.sanguo.base.util;

import java.util.ArrayList;
import java.util.List;

import com.game.sanguo.base.task.TaskUnit;

public class PipleLineTask {

	private List<TaskUnit> pipleQueue = new ArrayList<TaskUnit>(50);

	public void addFirst(TaskUnit r) {
		pipleQueue.add(0, r);
	}

	public void add(TaskUnit r) {
		pipleQueue.add(r);

	}

	public TaskUnit get() {
		if (pipleQueue.isEmpty()) {
			return null;
		}
		return pipleQueue.remove(0);
	}
}
