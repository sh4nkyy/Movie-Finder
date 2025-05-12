# COM1008 - Software Engineering


[Lines in square brackets are placeholders or instructions; you should replace them with the appropriate information. Please ensure no placeholder text is left in the final version (including this line, and the Lines below).]

[As this is a markdown file, you should edit it in a IDE or text editor that supports markdown - don't use Microsoft Word or other word processors. For more information on markdown, see: https://www.markdownguide.org/]

[** Before you submit, preview the rendered version of the file to ensure it is formatted correctly. Gitlab will render the markdown file as HTML, so please check the output is correct. Also, you can use an IDE or text editor that supports markdown to preview the file (e.g., VSCode). ** ] 

# Assignment 1 - Report

**Student Name: Ishan Nautiyal**

**Student ID: 6859183**

## 1.1 Proto Personas

### Persona 1

** Bilal Khan, Film and Creative Writing Undergraduate **

Bilal, age 18, is an undergraduate student studying Film and Creative Writing at University of Birmingham. He grew up in an apartment in Mitcham, the only child of a secondary school English teacher. Bilal earned his A Level in Art, Film Studies, Maths and English before enrolling at UoB. Prior to university, he worked part time as a music video film director, where he learned filming, video editing and the basics of social media marketing.

Bilal's experience with filming and cinema means he understands the importance of watching film and analysing cinema to enhance his own skills. He believes that the effective use technology can speed up his workflow and make him better at what he loves. His interest in flickfinder lies in the app's ability to filter movies by a variety of different fields, which he can use to analyse a variety of movies, and use the analysis to better his work.

### Persona 2

** Haaris Ali, Salesman and Movie Enthusiast **

Haaris, age 22, is a salesman working for British Gas in London. He was born in Twickenham where his father was a musician and his mother a software engineer. He has a degree in Computer Science from St Mary's University and enrolled into sales with hopes to become a tech Salesmen in the future. He gives piano lessons as a tutoring job on the side because he enjoys music. He grew up enjoying movies because of movie soundtracks, he particularly enjoyed the older Star Wars movies soundtracks by John Williams.

Haaris is a movie enthusiast and knows a lot about movies. He's watched a wide range of movies and a wide range of genres. He particularly enjoys the music used in movies as it adds to each scene and could foreshadow what will happen next. He is interested in using flickfinder to watch movies from different generations, good and bad, for his own entertainment and to add some more soundtracks to his spotify playlist!


## 1.2 Scenario

** Movie night with the Mrs **
Haaris is a movie enthusiast and has a girlfriend, who also loves movies. However there's one problem, they never know what movie to watch together. Their food gets cold because they spend too long trying to find a movie!

Haaris decides to use flickfinder to search for a new movie to watch, by year, actors starring in each movie and rating. Since his girlfriend doesn't really like old movies, they decided to filter out movies before 2000.

Haaris enjoys watching Cilian Murphy movies whereas his girlfriend prefers Tom Holland (she's loves Spider-Man). He needed a to find a movie that either has both of the actors, or one of them. So he chose filtered every actor except Tom Holland and Cilian Murphy.

A wide range of movies appeared and they needed to choose one. They used the ratings filter to decide whether a movie is worth watching or not. He set up a rating filter of no movies less than 6.7/10, and they found a movie called "A heart of the sea" which stars both Cilian Murphy and Tom Holland. They finally picked out the movie for the night, and their food was still warm - proving that by leveraging target filters, FlickFinder can help users find a movie swiftly.

## 1.3 User Stories

### User Story 1

** Bilal (Film and Creative Writing Undergraduate) **
As a student, I need to be able to analyse all types of film so that I can see what makes the difference between a good movie and a great movie.

### User Story 2

** Bilal (Film and Creative Writing Undergraduate) **
As a videographer, I want to see how videography has changed over the years and take inspiration from old and new movies so that I can use that inspiration to make my music videos stand out with more interesting creative direction.  

### User Story 3
** Bilal (Film and Creative Writing Undergraduate) **
As a student, I need to study the dialogues, music and visuals used in the best films to critically analyse the impact of a certain sounds, lines and visuals that give a scene more tension, build up the story and add depth to characters.

### User Story 4
** Haaris (Salesman and Movie Enthusiast) **
As a boyfriend, I need to find a selection of movies my girlfriend and I will both enjoy and that will expand our cinematic tastes, so that we can explore new genres together and enrich our shared movie experiences.

### User Story 5
** Haaris (Salesman and Movie Enthusiast) **
As a movie enthusiast, I want to find really random movies from random years which have really bad ratings so I can get a laugh out of how bad the movie is.

### User Story 6
** Haaris (Salesman and Movie Enthusiast) **
As a musician, I want to listen to beautifully composed soundtracks with scenery that matches the music to appreciate the conjunction of music and film as one piece of art.

## 2 Critical Analysis and Reflection

### 2.1 Reflection
The FlickFinder back-end project benefited from a clear MVC structure and Javalin’s lightweight routing, which made implementing and testing the must-have and should-have requirements straightforward. Using an in-memory SQLite database with a Seeder class allowed rapid iteration on DAOs and controllers without side effects, and our combination of unit and integration tests ensured confidence in each endpoint’s behaviour. Adopting test-driven development for C.1 and C.2 further improved reliability and catch edge-cases early.

However, there were areas for improvement. Error handling logic grew repetitive across controllers; centralising common response patterns (e.g., 500 vs. 404) could reduce duplication. Performance considerations—like adding indexes or caching—were deferred; future iterations should profile and optimise slow queries, especially as the real database scales to hundreds of thousands of records. Additionally, more rigorous input validation (beyond integer parsing) and comprehensive logging would strengthen robustness and debugging.

Looking ahead, bridging the back-end with a front-end client will uncover new requirements around pagination, rate-limiting, and user authentication. Incorporating user feedback loops—such as metrics on popular filters—can drive prioritisation of next features. Overall, this work established a solid foundation in API design, testing, and iterative improvement.

### 2.2 Professional Aspects
In building FlickFinder, we must attend not only to functionality but also to the broader professional responsibilities that underpin sustainable, secure and ethical software. First, code maintainability is critical: consistently formatted, self-documenting code reduces cognitive load and eases future enhancements and bug fixes. As Lecture 10 highlights, readable and uniform code “reduces the time required for comprehension and debugging” and ensures “smoother code evolution over time” (Lecture 10, slide 32). Closely linked is system architecture: a layered, loosely coupled design allows us to isolate components, minimise single points of failure and balance security with performance. Lecture 9 demonstrated how “distribution” can contain attacks and prevent cascading failures, while layered protection architectures enforce separation of concerns (Lecture 9, slide 43).

Legal and ethical compliance is equally paramount. Under the Data Protection Act 2018, any personal data must be “used fairly, lawfully and transparently”, kept only as long as necessary and safeguarded against unauthorised access (Lecture 10, slides 41–42) . Designing FlickFinder’s API with privacy by default and incorporating minimal data exposure not only aligns with UK GDPR but also fosters user trust. Professional bodies such as the British Computer Society set out codes of conduct—emphasising public interest, competence and integrity—that guide our ethical decision-making (Lecture 10, slide 48).

Sustainability must be integrated in every layer of our stack. As the “Green Software Brighton” talk explained, “you can’t improve what you don’t measure”, urging practitioners to instrument and monitor software’s carbon intensity (Lecture 10, slide 23). By adopting carbon-aware SDKs, optimising query performance and favouring caching and efficient algorithms, FlickFinder can reduce its environmental footprint. Finally, accessibility is a professional imperative: services must meet WCAG 2.2 Level AA to ensure all users—including those with disabilities—can interact with our app (Lecture 10, slide 35). Together, these considerations form a cohesive framework for delivering a responsible, future-proof FlickFinder API.

## 3. References
University of Surrey (2025) COM1028 Lecture 9: Security Engineering, 4 April. Available at: https://surreylearn.surrey.ac.uk/
University of Surrey (2025) COM1028 Lecture 10: Professional Practice and Sustainability, 11 April. Available at: https://surreylearn.surrey.ac.uk/
