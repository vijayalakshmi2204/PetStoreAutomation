package api.utilities;


	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;

	public class ExtentReportManager {

	    private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	            reporter.config().setReportName("Automation Test Report");
	            reporter.config().setDocumentTitle("Test Results");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            extent.setSystemInfo("Application","Petstore");
	            extent.setSystemInfo("operating systems","Windows 11");
	            extent.setSystemInfo("Tester", "Vijayalakshmi");
	            extent.setSystemInfo("Environment", "QA");
	        }
	        return extent;
	    }
	}

