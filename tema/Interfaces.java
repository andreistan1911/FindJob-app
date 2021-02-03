package tema;

interface Observer {
	public void update(Notification notification);
}

interface Subject {
	public void addObserver(User user);
	public void removeObserver(User user);
	public void notifyAllObservers();
}
