// Traditional Singleton eager initialization without thread safety
class SingletonEager {
	private static SingletonEager instance = new SingletonEager();
	
	private SingletonEager(){}
	
	public static SingletonEager getInstance(){
		return instance;
	}
	
	public void display() { System.out.println("SingletonEager"); }
}

// Traditional Singleton lazy initialization without thread safety
class SingletonLazy {
	private static SingletonLazy instance = null;

	private SingletonLazy(){}
	
	public static SingletonLazy getInstance() {
		if(instance==null) {
			instance=new SingletonLazy();
		}
		return instance;
	}
	
	public void display() { System.out.println("SingletonLazy"); }
}

// Singleton eager initialization with thread safety
class SingletonEagerThreadSafe {
	private static SingletonEagerThreadSafe instance = new SingletonEagerThreadSafe();
	
	private SingletonEagerThreadSafe() {}
	
	public static synchronized SingletonEagerThreadSafe getInstance() {
		return instance;
	}
	
	public void display() { System.out.println("SingletonEagerThreadSafe");}
}

// Singleton lazy initialization with thread safety
class SingletonLazyThreadSafe {
	private static SingletonLazyThreadSafe instance = null;
	
	private SingletonLazyThreadSafe() {}
	
	public static synchronized SingletonLazyThreadSafe getInstance() {
		if(instance==null) {
			instance=new SingletonLazyThreadSafe();
		}
		return instance;
	}
	
	public void display() { System.out.println("SingletonLazyThreadSafe"); }
}

// Singleton best practise - Double checked locking based implementation
class SingletonBestPractise {
	/* 
	 Instance has been declared volatile which ensures that multiple threads offer the object variable 
	 (instance) correctly when is is being initialized to Singleton instance 
	*/
	private volatile static SingletonBestPractise instance = null;
	
	private SingletonBestPractise() {}
	
	public static SingletonBestPractise getInstance() {
		// Fitst null check is there because once the instance is created , synchronization is no longer useful
		if(instance==null) {
			// Here we acquire the lock only when the instance is null
			synchronized(SingletonBestPractise.class) {
				if(instance==null) {
					instance=new SingletonBestPractise();
				}
			}
		}
		return instance;
	}
	
	public void display() { System.out.println("SingletonBestPractise"); }
}

class SingletonPatternDemo {
	public static void main(String[] args) {
		SingletonEager singletonEager = SingletonEager.getInstance();
		singletonEager.display();
		
		SingletonLazy singletonLazy = SingletonLazy.getInstance();
		singletonLazy.display();
		
		SingletonEagerThreadSafe singletonEagerThreadSafe = SingletonEagerThreadSafe.getInstance();
		singletonEagerThreadSafe.display();
		
		SingletonLazyThreadSafe singletonLazyThreadSafe = SingletonLazyThreadSafe.getInstance();
		singletonLazyThreadSafe.display();
		
		SingletonBestPractise singletonBestPractise = SingletonBestPractise.getInstance();
		singletonBestPractise.display();
	}
}