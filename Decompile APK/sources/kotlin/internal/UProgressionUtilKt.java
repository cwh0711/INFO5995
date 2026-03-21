package kotlin.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

/* JADX INFO: compiled from: UProgressionUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class UProgressionUtilKt {
    /* JADX INFO: renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1366differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM = UByte$$ExternalSyntheticBackport0.m(i, i3);
        int iM2 = UByte$$ExternalSyntheticBackport0.m(i2, i3);
        int iCompare = Integer.compare(iM ^ Integer.MIN_VALUE, iM2 ^ Integer.MIN_VALUE);
        int iM243constructorimpl = UInt.m243constructorimpl(iM - iM2);
        return iCompare >= 0 ? iM243constructorimpl : UInt.m243constructorimpl(iM243constructorimpl + i3);
    }

    /* JADX INFO: renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1367differenceModulosambcqE(long j, long j2, long j3) {
        long jM216m = UByte$$ExternalSyntheticBackport0.m216m(j, j3);
        long jM216m2 = UByte$$ExternalSyntheticBackport0.m216m(j2, j3);
        int iCompare = Long.compare(jM216m ^ Long.MIN_VALUE, jM216m2 ^ Long.MIN_VALUE);
        long jM322constructorimpl = ULong.m322constructorimpl(jM216m - jM216m2);
        return iCompare >= 0 ? jM322constructorimpl : ULong.m322constructorimpl(jM322constructorimpl + j3);
    }

    /* JADX INFO: renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1369getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i2 : UInt.m243constructorimpl(i2 - m1366differenceModuloWZ9TVnA(i2, i, UInt.m243constructorimpl(i3)));
        }
        if (i3 < 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i2 : UInt.m243constructorimpl(i2 + m1366differenceModuloWZ9TVnA(i, i2, UInt.m243constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* JADX INFO: renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1368getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j2 : ULong.m322constructorimpl(j2 - m1367differenceModulosambcqE(j2, j, ULong.m322constructorimpl(j3)));
        }
        if (j3 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j2 : ULong.m322constructorimpl(j2 + m1367differenceModulosambcqE(j, j2, ULong.m322constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
