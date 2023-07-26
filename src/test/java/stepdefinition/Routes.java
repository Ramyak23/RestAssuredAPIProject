package stepdefinition;

public class Routes {
 
	public static String BASE_URI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	
	//BATCH MODULE
	public static String SAVEENDPOINT="/batches";
	public static String GETBATCHBYID=BASE_URI+"/batches/batchId/";
	public static String GETBATCHBYNAME=BASE_URI+"/batches/batchName/";
	public static String GETBATCHBYPRGMID=BASE_URI+"/batches/program/";
	public static String PUTBATCH=BASE_URI+"/batches/";
	public static String DELETEBATCH=BASE_URI+"/batches/";
	
	//USER MODULE
	public static String POSTUSER="/users/users/roleStatus";
	public static String GETALLUSER=BASE_URI+"/users/users";
	public static String GETUSERBYID=BASE_URI+"/users/users/";
	public static String GETALLUSERWITHROLES=BASE_URI+"/users/users/roles";
	public static String GETALLSTAFF=BASE_URI+"/users/users/getAllStaff";
	public static String UPDATEUSER=BASE_URI+"/users/users/";
	public static String PUTROLESTATUS=BASE_URI+"/users/users/roleStatus/";
	public static String PUTPRGMBATCHSTATUS=BASE_URI+"/users/users/roleProgramBatchStatus/";
	public static String DELETEUSER=BASE_URI+"/users/users/";
	
	//ASSIGNMENT MODULE
	public static String GETALLASSIGNMENT=BASE_URI+"/assignments";
	public static String GETASSIGNMENTBYID=BASE_URI+"/assignments/";
	public static String GETASSIGNFORBATCH=BASE_URI+"/assignments/batch/";
	public static String POSTASSIGNMENT="/assignments";
	public static String PUTASSIGNMENT=BASE_URI+"/assignments/";
	public static String DELETEASSIGNMENT=BASE_URI+"/assignments/";
	
	//ASSIGNMENT SUBMIT MODULE
	public static String GETALLSUBMISSION=BASE_URI+"/assignmentsubmission";
	public static String GETGRADESBYASSIGNID=BASE_URI+"/assignmentsubmission/getGrades/";
	public static String GETGRADESBYSTUID=BASE_URI+"/assignmentsubmission/getGradesByStudentId/";
	public static String GETGRADESBYBATCHID=BASE_URI+"/assignmentsubmission/grades/";
	public static String GETSUBBYUSERID=BASE_URI+"/assignmentsubmission/student/";
	public static String GETSUBBYBATCHID=BASE_URI+"/assignmentsubmission/studentbatch/";
	public static String POSTSUBMIT="/assignmentsubmission";
	public static String PUTRESUBMIT=BASE_URI+"/assignmentsubmission/";
	public static String PUTGRADEASSIGN=BASE_URI+"/assignmentsubmission/gradesubmission/";
	public static String DELETESUBMIT=BASE_URI+"/assignmentsubmission/";
	
	
	
	
	
	
	
}
