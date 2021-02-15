
	Trail and User CONTAINERS
	
	
	
			
		TrailsContainer- 
		
			TreeMap<String, LinkedList<Trail>>
				Allows for search for trails by partial trailname match
				
			LinkedList<Trail>
				Stores all trails with no demand for unique Keys, This is not used currently, but could be used if
				administration wanted to track the order of trails being created/added to the App
		
	
					Arguably, The TreeMap could have been substituted by a HashMap of LinkedLists, since the cap on Trails was 50,000,
					but if this cap was ever to be changed, or removed, there would be many changes to be made across the rest of the application
					in order to change the trail capacity of the app, only one variable (TrailContainer.count) must be changed
					If the filtering was only to get exact matches instead of partial, I could see a use of a hashmap to return a list of Trails at O(1),
					but since the matches are partial, I see no added efficiency in using a hashmap.
			
			
		UserContainer
			TreeMap<String, User>
				Allows for searching of a user at O(log(n))
			
			Since the capacity of Users is 'infinite', and searching by username is required, TreeMap is a great option.  Allowing for a user to be retrieved at O(log(n))
			is efficient when retrieving a single user.  When finding users by partial match, the efficiency drops to O(n) as each user must be checked
			
			
		TotalTrailHistory
			*Currently not used* but could be implemented for admin/managers to view and filter all instances of trails hiked.  The Linked List retains the chronological order
			and would allow for filtering at O(n)
			
			
		
	Searching
			
		Users 
			Users are searched 2 separate ways:
				
				(AdminControls.fxml) 
				In the Admin Controls Scene, the Admin is able to quickly find account info on a user by partial match
				of usernames
						O(n)
				(AdminUserView.fxml)
				In the Admin User Scene, The Admin can input the key (the username) to retrieve the account information
					O(log(n))
			
					
		Trails (FindHikeView.fxml, AdminHikeView.fxml)
				
			Trails can be sorted in the Find Trail Scene and in the Admin Trail Scene
				The first thing that is checked when filtering the list of trails is the Trail Name query, If the query is 
				blank, the entire set of trails is passed to the next set of filters.
				if the query has content, the TraiilContainer TreeMap is traversed finding each key that contains the query.
				The LinkedList inside the TreeMap's node is then traversed collecting each trail (all of which have a trailname 
				matching the key).
							
					This step in the filtering process is O(n), but is a more efficient O(n) than a linear search except at a worst case
					where every Trail matches the Query.
					
				This new list of trails is passed through a method that checks the criteria of gain, length, difficulty and type.
				Gain Filter returns true if the trail's gain is greater than the minGain (if specified) and less than the maxGain(if specified).
				Length Filter returns true if the trail's length is greater than the minLength (if specified) and less than the maxLength(if specified).
				Type Filter returns true if a trail's type matches a type selected (if none are selected it returns true)
				Difficulty Filter returns true if a trail's difficulty matches a type selected (if none are selected it returns true)
				
					each of these filters is O(1),
				leaving the Filter to be O(n).  This Filter is applied upon every change to any of the filter fields
			
			
		Trail History (HomeView.fxml, AdminUserView.fxml)
				
			Trail History uses a Linked List in order to display the history chronologically
			finding a specific Trail that was hiked would be O(n) but is not an implementation in the program... if it were, it would likely never reach a point
			in which the length of the LinkedList caused notable differences in speed.
		
		
		
	Hierarchy
		
		User: User has no special functionality, They are able to search hikes, log hikes, save a photo to a hike, update their personal information
		update their profile picture, and view their trail history
		
		Admin: Admin extends User and has all functions of User and additional functionalities including: promoting a user to admin, updating/editing, adding and removing users,
		updating adding and removing trails from the app.
		
		Manager: Manager extends Admin and has additional functionalities: They are able to demote Admins. This was implemented to prevent an admin from being
		demoted by a user that they had just promoted.  Managers have full control over the hierarchy beneath them but would likely only interact with the admin layer,
		and possibly the trail editor.
		
		
		