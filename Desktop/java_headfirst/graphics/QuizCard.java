public class QuizCard
	{
		String question,answer;

		public QuizCard(String q, String a)
		{	question = q;
			answer = a;
		}

		public String getQuestion()
		{
			return question;
		}

		public String getAnswer()
		{
			return answer;
		}
	}