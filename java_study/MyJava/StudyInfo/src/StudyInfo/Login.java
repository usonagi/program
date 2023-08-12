package StudyInfo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/** ´°¿ÚÓ¦ÓÃ  µÇÂ¼½çÃæ
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Login.class, "/image/\u6E56\u5DE5.jpg"));
		shell.setSize(669, 415);
		shell.setText("\u6821\u56ED\u6D3B\u52A8\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Adobe ¿¬Ìå Std R", 16, SWT.NORMAL));
		label.setBounds(136, 44, 387, 30);
		label.setText("\u6B22\u8FCE\u6765\u5230\u6821\u56ED\u6D3B\u52A8\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ Light", 12, SWT.NORMAL));
		label_1.setBounds(110, 139, 57, 30);
		label_1.setText("\u7528\u6237\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(175, 143, 149, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ Light", 12, SWT.NORMAL));
		lblNewLabel.setBounds(110, 204, 57, 30);
		lblNewLabel.setText("\u5BC6\u7801\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(175, 204, 149, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(110, 288, 98, 30);
		btnNewButton.setText("\u767B\u5F55");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_1.setBounds(276, 288, 98, 30);
		btnNewButton_1.setText("\u6CE8\u518C");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(443, 288, 98, 30);
		btnNewButton_2.setText("\u9000\u51FA");

	}
}
