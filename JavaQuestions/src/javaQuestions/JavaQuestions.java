/**
 * Constructs the questions based on user input.
 * 
 * @author Sudharaka
 *
 */
package javaQuestions;

public class JavaQuestions {
	private String questions;

	/**
	 * @return the questions
	 */
	public String getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(String questions) {
		if (questions.equals("y")){
			this.questions = "SecondQuestion";
		}else if(questions.equals("n")){
			this.questions = "ThirdQuestion";
		}
	}
}
