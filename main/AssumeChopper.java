package scripts.woodcutter.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.NumberFormat;
import java.util.Locale;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.Script;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.ingame.TreeType;
import scripts.woodcutter.api.types.top.Worker;
import scripts.woodcutter.api.types.top.WorkerFactory;
import scripts.woodcutter.api.types.variables.Vars;
import scripts.woodcutter.gui.GUI;

public class AssumeChopper extends Script implements Painting,
		MessageListening07 {

	private Worker current_worker;
	private int starting_exp;
	private int starting_level;

	@Override
	public void run() {
		General.useAntiBanCompliance(true);
		Vars.get();
		this.starting_exp = Skills.getXP(SKILLS.WOODCUTTING);
		this.starting_level = Skills.getActualLevel(SKILLS.WOODCUTTING);
		GUI gui = new GUI();
		gui.setVisible(true);
		while (gui.isVisible())
			General.sleep(80, 120);
		if (Vars.get().progression_type != null)
			Vars.get().progression_type.removeInvalidMoves();
		this.current_worker = WorkerFactory.CHOPPER.getWorker();
		Camera.setCameraAngle(90);
		while (loop())
			General.sleep(50, 100);
	}

	private boolean loop() {
		if (current_worker == null)
			return false;
		Vars.get().abc_util.performTimedActions(SKILLS.WOODCUTTING);
		DescriptiveBoolean execution_test = current_worker.shouldExecute();
		if (!execution_test.getBoolean())
			current_worker = execution_test.getReason().getNextWorker();
		else
			current_worker.execute();
		return true;
	}

	@Override
	public void onPaint(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D gd = (Graphics2D) g;
		final Color color2 = new Color(0, 0, 0);
		String[] infoArray = {
				"Runtime: " + Timing.msToString(super.getRunningTime()),
				"Logs chopped: "
						+ nf.format((TreeType.getTotalNumberChopped()))
						+ " ("
						+ nf.format(getPerHour(TreeType.getTotalNumberChopped()))
						+ "/HR)",
				"Experience Gained: "
						+ nf.format((Skills.getXP(SKILLS.WOODCUTTING) - starting_exp))
						+ " ("
						+ nf.format(getPerHour(Skills.getXP(SKILLS.WOODCUTTING)
								- starting_exp)) + "/HR)",
				"Levels gained: "
						+ (Skills.getActualLevel(SKILLS.WOODCUTTING) - starting_level)
						+ " ("
						+ getPerHour(Skills.getActualLevel(SKILLS.WOODCUTTING)
								- starting_level) + "/HR)" };
		int c = 0;
		for (String s : infoArray) {
			gd.setColor(new Color(255, 255, 255, 150));
			int length = getStringPixelLength(s, g);
			gd.fillRoundRect(5, 270 + 17 * c, length + 6, 14, 5, 5);
			gd.setColor(color2);
			gd.drawRoundRect(5, 270 + 17 * c, length + 6, 14, 5, 5);
			gd.drawString(s, 7, 281 + 17 * c);
			c++;
		}
	}

	private int getStringPixelLength(String s, Graphics g) {
		int x = 0;
		for (int i = 0; i < s.length(); i++)
			x += g.getFontMetrics().charWidth(s.charAt(i));
		return x;
	}

	private static final NumberFormat nf = NumberFormat.getInstance(Locale.US);

	private int getPerHour(int amount) {
		return (int) ((3600000.0 / (double) super.getRunningTime() * amount));
	}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {
	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void serverMessageReceived(String arg0) {
		if (arg0.contains("You get some"))
			Vars.get().current_location.getTree().incrementAmountChopped();
	}

	@Override
	public void tradeRequestReceived(String arg0) {
	}

}
